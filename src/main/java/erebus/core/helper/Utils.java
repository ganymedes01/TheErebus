package erebus.core.helper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import erebus.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.oredict.OreDictionary;

public class Utils {

	public static boolean rightClickItemAt(World world, BlockPos pos, EnumHand hand, EnumFacing side, ItemStack stack) {
		if (world.isRemote || stack.isEmpty() || stack.getItem() == null)
			return false;
		EntityPlayer player = getPlayer(world);
		player.replaceItemInInventory(0, stack);
		try {
			return stack.getItem().onItemUse(player, world, pos, hand, side, 0, 0, 0) != null;
		} finally {
			player.replaceItemInInventory(0, ItemStack.EMPTY);
		}
	}

	public static EntityPlayer getPlayer(World world) {
		if (world.isRemote || !(world instanceof WorldServer))
			return null;
		return FakePlayerFactory.get((WorldServer) world, new GameProfile(UUID.nameUUIDFromBytes(Reference.MOD_ID.getBytes()), "[" + Reference.MOD_ID + "]"));
	}

	public static final int getFlowerMetadata(Object obj) {
		int meta = -1;
		if (obj instanceof ItemStack)
			meta = ((ItemStack) obj).getItemDamage();
		else if (obj instanceof Integer)
			meta = (Integer) obj;

		if (meta >= 2 && meta <= 8 || meta == 14)
			meta++;
		else if (meta >= 9 && meta <= 13)
			meta += 2;

		return meta;
	}

	public static final void breakBlockWithParticles(World world, BlockPos pos, IBlockState state) {
		playBreakParticles(world, pos, state);
		world.setBlockToAir(pos);
	}

	public static final void breakBlockWithParticles(World world, BlockPos pos) {
		breakBlockWithParticles(world, pos, world.getBlockState(pos));
	}
	
	public static void playBreakParticles(World world, BlockPos pos, IBlockState state) {
		world.playEvent(null, 2001, pos, Block.getIdFromBlock(state.getBlock()));
	}

	public static void playBreakParticles(World world, BlockPos pos) {
		world.playEvent(null, 2001, pos, Block.getIdFromBlock(world.getBlockState(pos).getBlock()) + (world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)) << 12));
	}

	public static final void dropStack(World world, BlockPos pos, ItemStack is) {
		if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops")) {
			float f = 0.7F;
			double d0 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
			double d1 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
			double d2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
			EntityItem entityitem = new EntityItem(world, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, is);
			entityitem.setPickupDelay(10);
			world.spawnEntity(entityitem);
		}
	}

	public static final int getColour(int R, int G, int B) {
		return new Color(R, G, B).getRGB() & 0x00ffffff;
	}

	@SuppressWarnings("unchecked")
	public static final <T> T getTileEntity(IBlockAccess world, BlockPos pos, Class<T> cls) {
		TileEntity tile = world.getTileEntity(pos);
		if (!cls.isInstance(tile))
			return null;
		return (T) tile;
	}

	public static void dropStackNoRandom(World world, BlockPos pos, ItemStack stack) {
		if (!world.isRemote && !stack.isEmpty() && world.getGameRules().getBoolean("doTileDrops")) {
			EntityItem entityItem = new EntityItem(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, stack);
			entityItem.motionX = 0;
			entityItem.motionY = 0;
			entityItem.motionZ = 0;
			entityItem.setPickupDelay(10);
			world.spawnEntity(entityItem);
		}
	}

	public static int[] getSlotsFromSide(IInventory iinventory, EnumFacing side) {
		if (iinventory == null)
			return null;

		if (iinventory instanceof ISidedInventory)
			return ((ISidedInventory) iinventory).getSlotsForFace(side);
		else {
			int[] slots = new int[iinventory.getSizeInventory()];
			for (int i = 0; i < slots.length; i++)
				slots[i] = i;
			return slots;
		}
	}

	/**
	 * Extracts 1 item from the first found stack
	 *
	 * @param iinventory
	 * @param side
	 * @return extracted stack
	 */
	public static ItemStack extractFromInventory(IInventory iinventory, EnumFacing side) {
		return extractFromInventory(iinventory, side, 1);
	}

	/**
	 * Extracts a stack with size the same or smaller of @param maxStackSize
	 *
	 * @param iinventory
	 * @param side
	 * @param maxStackSize
	 * @return extracted stack
	 */
	public static ItemStack extractFromInventory(IInventory iinventory, EnumFacing side, int maxStackSize) {
		IInventory invt = getInventory(iinventory);
		return extractFromSlots(invt, side, maxStackSize, getSlotsFromSide(invt, side));
	}

	private static ItemStack extractFromSlots(IInventory iinventory, EnumFacing side, int maxStackSize, int[] slots) {
		for (int slot : slots) {
			ItemStack invtStack = iinventory.getStackInSlot(slot);
			if (!invtStack.isEmpty())
				if (!(iinventory instanceof ISidedInventory) || ((ISidedInventory) iinventory).canExtractItem(slot, invtStack, side)) {
					ItemStack copy = invtStack.copy();
					if (maxStackSize <= 0)
						iinventory.setInventorySlotContents(slot, ItemStack.EMPTY);
					else {
						int amount = Math.min(maxStackSize, invtStack.getCount());
						invtStack.shrink(amount);
						copy.setCount(amount);
						if (invtStack.isEmpty())
							iinventory.setInventorySlotContents(slot, ItemStack.EMPTY);
					}
					return copy;
				}
		}
		return null;
	}

	public static boolean addEntitytoInventory(IInventory iinventoryTo, EntityItem entity) {
		if (entity == null)
			return false;

		boolean flag = addItemStackToInventory(iinventoryTo, entity.getItem());
		if (flag)
			entity.setDead();
		else if (entity.getItem().isEmpty())
			entity.setDead();
		return flag;
	}

	public static boolean addItemStackToInventory(IInventory iinventoryTo, ItemStack stack) {
		return addItemStackToInventory(iinventoryTo, stack, EnumFacing.DOWN);
	}

	public static boolean addItemStackToInventory(IInventory iinventoryTo, ItemStack stack, EnumFacing side) {
		if (iinventoryTo == null)
			return false;

		if (stack.isEmpty())
			return false;

		IInventory invtTo = getInventory(iinventoryTo);
		return addToSlots(invtTo, stack, side, getSlotsFromSide(invtTo, side));
	}

	private static boolean addToSlots(IInventory iinventory, ItemStack stack, EnumFacing side, int[] slots) {
		for (int slot : slots) {
			if (iinventory instanceof ISidedInventory) {
				if (!((ISidedInventory) iinventory).canInsertItem(slot, stack, side))
					continue;
			} else if (!iinventory.isItemValidForSlot(slot, stack))
				continue;

			if (iinventory.getStackInSlot(slot).isEmpty()) {
				iinventory.setInventorySlotContents(slot, stack.copy());
				stack.setCount(0);
				return true;
			} else {
				ItemStack invtStack = iinventory.getStackInSlot(slot);
				if (invtStack.getCount() < Math.min(invtStack.getMaxStackSize(), iinventory.getInventoryStackLimit()) && areStacksTheSame(invtStack, stack, false)) {
					invtStack.grow(stack.getCount());
					if (invtStack.getCount() > invtStack.getMaxStackSize()) {
						stack.setCount(invtStack.getMaxStackSize() - invtStack.getCount());
						invtStack.setCount(invtStack.getMaxStackSize());
					} else
						stack.setCount(0);
					return true;
				}
			}
		}
		return false;
	}

	public static boolean areStacksSameOre(ItemStack stack1, ItemStack stack2) {
		if (stack1.isEmpty() || stack2.isEmpty())
			return false;
		if (areStacksTheSame(stack1, stack2, false))
			return true;

		List<String> ores1 = getOreNames(stack1);
		List<String> ores2 = getOreNames(stack2);

		if (ores1.isEmpty() || ores2.isEmpty())
			return false;
		for (String ore2 : ores2)
			if (ores1.contains(ore2))
				return isIntercheageableOreName(ore2);
		return false;
	}

	private static final String[] orePrefixes = new String[] { "dust", "ingot", "ore", "block", "gem", "nugget", "shard", "plate", "gear", "stickWood" };

	private static boolean isIntercheageableOreName(String name) {
		for (String prefix : orePrefixes)
			if (name.startsWith(prefix))
				return true;
		return false;
	}

	public static List<String> getOreNames(ItemStack stack) {
		List<String> list = new ArrayList<String>();
		for (int id : OreDictionary.getOreIDs(stack))
			list.add(OreDictionary.getOreName(id));

		return list;
	}

	public static boolean isItemOre(ItemStack stack, String ore) {
		int oreID = OreDictionary.getOreID(ore);
		for (int id : OreDictionary.getOreIDs(stack))
			if (id == oreID)
				return true;
		return false;
	}

	public static boolean areStacksTheSame(ItemStack stack1, ItemStack stack2, boolean matchSize) {
		if (stack1.isEmpty() || stack2.isEmpty())
			return false;

		if (stack1.getItem() == stack2.getItem())
			if (stack1.getItemDamage() == stack2.getItemDamage() || isWildcard(stack1.getItemDamage()) || isWildcard(stack2.getItemDamage()))
				if (!matchSize || stack1.getCount() == stack2.getCount()) {
					if (stack1.hasTagCompound() && stack2.hasTagCompound())
						return stack1.getTagCompound().equals(stack2.getTagCompound());
					return stack1.hasTagCompound() == stack2.hasTagCompound();
				}
		return false;
	}

	private static boolean isWildcard(int meta) {
		return meta == OreDictionary.WILDCARD_VALUE;
	}

	public static IInventory getInventory(IInventory inventory) {
		if (inventory instanceof TileEntityChest) {
			TileEntityChest chest = (TileEntityChest) inventory;
			TileEntityChest adjacent = null;
			if (chest.adjacentChestXNeg != null)
				adjacent = chest.adjacentChestXNeg;
			if (chest.adjacentChestXNeg != null)
				adjacent = chest.adjacentChestXNeg;
			if (chest.adjacentChestXPos != null)
				adjacent = chest.adjacentChestXPos;
			if (chest.adjacentChestZNeg != null)
				adjacent = chest.adjacentChestZNeg;
			if (chest.adjacentChestZPos != null)
				adjacent = chest.adjacentChestZPos;

			if (adjacent != null)
				return new InventoryLargeChest("", (ILockableContainer) inventory, adjacent);
		}
		return inventory;
	}

	public static void dropInventoryContents(TileEntity tile) {
		if (tile == null || !(tile instanceof IInventory))
			return;
		IInventory iinventory = (IInventory) tile;
		for (int i = 0; i < iinventory.getSizeInventory(); i++) {
			ItemStack stack = iinventory.getStackInSlot(i);
			if (!stack.isEmpty() && stack.getItem() != null && stack.getCount()> 0) {
				dropStack(tile.getWorld(), tile.getPos(), stack.copy());
				iinventory.setInventorySlotContents(i, ItemStack.EMPTY);
			}
		}
		tile.markDirty();
	}

	public static boolean inventoryContains(IInventory iinventory, ItemStack stack, boolean ignoreSize) {
		return inventoryContains(iinventory, stack, ignoreSize, getSlotsFromSide(iinventory, EnumFacing.DOWN));
	}

	public static boolean inventoryContains(IInventory iinventory, ItemStack stack, boolean ignoreSize, int... slots) {
		if (stack.isEmpty())
			return false;
		iinventory = getInventory(iinventory);

		int totalSize = 0;
		for (int slot : slots) {
			ItemStack invtStack = iinventory.getStackInSlot(slot);
			if (areStacksTheSame(invtStack, stack, false)) {
				if (ignoreSize)
					return true;
				totalSize += invtStack.getCount();
			}
			if (totalSize >= stack.getCount())
				return true;
		}

		return false;
	}

	public static boolean deleteFromInventory(IInventory iinventory, EnumFacing side, ItemStack stack) {
		return deleteFromSlots(iinventory, stack, getSlotsFromSide(iinventory, side));
	}

	public static boolean deleteFromSlots(IInventory iinventory, ItemStack stack, int... slots) {
		iinventory = getInventory(iinventory);

		if (!inventoryContains(iinventory, stack, false))
			return false;
		int totalDel = 0;
		for (int slot : slots) {
			ItemStack invtStack = iinventory.getStackInSlot(slot);
			if (areStacksTheSame(invtStack, stack, false) || areStacksSameOre(invtStack, stack))
				if (invtStack.getCount() >= stack.getCount()) {
					invtStack.shrink(stack.getMaxStackSize() - invtStack.getCount());
					if (invtStack.getCount() <= 0)
						iinventory.setInventorySlotContents(slot, getContainer(stack));
					return true;
				} else {
					totalDel += invtStack.getCount();
					iinventory.setInventorySlotContents(slot, getContainer(stack));
				}
			if (totalDel == stack.getCount())
				return true;
		}
		return false;
	}

	public static ItemStack getContainer(ItemStack stack) {
		return stack.getItem().hasContainerItem(stack) ? stack.getItem().getContainerItem(stack) : ItemStack.EMPTY;
	}

	public static final LinkedHashMap<Short, Short> getEnchantments(ItemStack stack) {
		LinkedHashMap<Short, Short> map = new LinkedHashMap<Short, Short>();
		NBTTagList list = stack.getItem() == Items.ENCHANTED_BOOK ? stack.getEnchantmentTagList() : stack.getEnchantmentTagList(); //will come back to this

		if (list != null)
			for (int i = 0; i < list.tagCount(); i++) {
				NBTTagCompound tag = list.getCompoundTagAt(i);
				map.put(tag.getShort("id"), tag.getShort("lvl"));
			}
		return map;
	}
}