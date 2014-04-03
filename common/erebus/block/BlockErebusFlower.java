package erebus.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.helper.Utils;

public class BlockErebusFlower extends Block {

	public enum FLOWER_TYPE {
		EXPLODING_STIGMA, STEM, BLACK_PETAL, RED_PETAL, BROWN_PETAL, BLUE_PETAL, PURPLE_PETAL, CYAN_PETAL, LIGHT_GRAY_PETAL, GRAY_PETAL, PINK_PETAL, YELLOW_PETAL, LIGHT_BLUE_PETAL, MAGENTA_PETAL, ORANGE_PETAL, WHITE_PETAL
	}

	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	public BlockErebusFlower(int id) {
		super(id, Material.plants);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
		if (meta == FLOWER_TYPE.EXPLODING_STIGMA.ordinal())
			world.createExplosion(player, x, y, z, 3, world.getGameRules().getGameRuleBooleanValue("mobGriefing"));
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, meta, fortune);

		if (meta == FLOWER_TYPE.EXPLODING_STIGMA.ordinal())
			ret.add(new ItemStack(Item.gunpowder));
		else
			ret.add(new ItemStack(blockID, 1 + new Random().nextInt(3), meta));

		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		return getRenderColor(world.getBlockMetadata(x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		if (meta == FLOWER_TYPE.EXPLODING_STIGMA.ordinal() || meta == FLOWER_TYPE.STEM.ordinal())
			return super.getRenderColor(meta);

		float[] colour = EntitySheep.fleeceColorTable[BlockColored.getBlockFromDye(Utils.getFlowerMetadata(meta - 2))];
		return Utils.getColour((int) (colour[0] * 255), (int) (colour[1] * 255), (int) (colour[2] * 255));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < 16; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return icons[Math.min(meta, 2)];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		icons = new Icon[3];
		for (int i = 0; i < icons.length; i++)
			icons[i] = reg.registerIcon("erebus:erebusFlower" + i);
	}
}