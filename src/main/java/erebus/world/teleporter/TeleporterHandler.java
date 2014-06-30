package erebus.world.teleporter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityMinecartContainer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.core.handler.ConfigHandler;

public final class TeleporterHandler{
	private static TeleporterHandler INSTANCE = new TeleporterHandler();
	
	public static void init(){
		MinecraftForge.EVENT_BUS.register(INSTANCE);
	}
	
	public static void transferToOverworld(Entity entity){
		INSTANCE.transferEntity(entity,0);
	}
	
	public static void transferToErebus(Entity entity){
		INSTANCE.transferEntity(entity,ConfigHandler.erebusDimensionID);
	}
	
	private TeleporterErebus teleportToOverworld;
	private TeleporterErebus teleportToErebus;
	
	private TeleporterHandler(){}
	
	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load e){
		if (!(e.world instanceof WorldServer))return;
		
		WorldServer world = (WorldServer)e.world;
		
		if (world.provider.dimensionId == 0){
			world.customTeleporters.add(teleportToOverworld = new TeleporterErebus(world));
		}
		else if (world.provider.dimensionId == ConfigHandler.erebusDimensionID){
			world.customTeleporters.add(teleportToErebus = new TeleporterErebus(world));
		}
		
		System.out.println("added to "+e.world);
	}
	
	private void transferEntity(Entity entity, int dimensionId){
		if (dimensionId != 0 && dimensionId != ConfigHandler.erebusDimensionID)throw new IllegalArgumentException("Supplied invalid dimension ID into Erebus teleporter: "+dimensionId);
		
		World world = entity.worldObj;
		
		if (!world.isRemote && !entity.isDead){
			if (entity instanceof EntityPlayerMP){
				if (entity instanceof FakePlayer)return;

				EntityPlayerMP player = (EntityPlayerMP)entity;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player,dimensionId,dimensionId == 0 ? teleportToOverworld : teleportToErebus);
				player.timeUntilPortal = 60; // TODO change back to 10 once BlockErebusPortal is fixed
				/*player.lastExperience = -1;
				player.lastHealth = -1.0F;
				player.lastFoodLevel = -1;*/
			}
			else if (!(entity instanceof EntityMinecartContainer)){ // TODO we cannot handle this, would result in container breaking in both worlds and duplicate items; find some sneaky solution around this issue
				world.theProfiler.startSection("changeDimension");
				
				MinecraftServer mcServer = MinecraftServer.getServer();
				WorldServer worldCurrent = mcServer.worldServerForDimension(entity.dimension);
				WorldServer worldTarget = mcServer.worldServerForDimension(dimensionId);
				entity.dimension = dimensionId;
	
				world.removeEntity(entity);
				entity.isDead = false;
				world.theProfiler.startSection("reposition");
				mcServer.getConfigurationManager().transferEntityToWorld(entity,dimensionId,worldCurrent,worldTarget,dimensionId == 0 ? teleportToOverworld : teleportToErebus);
				world.theProfiler.endStartSection("reloading");
				Entity newEntity = EntityList.createEntityByName(EntityList.getEntityString(entity),worldTarget);
	
				if (newEntity != null){
					newEntity.copyDataFrom(entity,true);	
					worldTarget.spawnEntityInWorld(newEntity);
				}
	
				entity.isDead = true;
				world.theProfiler.endSection();
				worldCurrent.resetUpdateEntityTick();
				worldTarget.resetUpdateEntityTick();
				world.theProfiler.endSection();
				
				entity.timeUntilPortal = entity.getPortalCooldown();
			}
		}
	}
}