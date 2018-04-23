package com.theundertaker11.ice9;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockIce9 extends BlockIce {

	public BlockIce9(String name) {
		super();
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		for (BlockPos tPos : getAllTouching(pos)) {
			Block block = worldIn.getBlockState(tPos).getBlock();
			if (worldIn.getBlockState(tPos).getBlock() == Blocks.WATER) {
				worldIn.setBlockState(tPos, Ice9.ice9.getDefaultState());
			}
			if (block == Blocks.LAVA||block == Blocks.FLOWING_LAVA) {
				worldIn.setBlockState(pos, Blocks.WATER.getDefaultState());
			}
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (Config.fastmode) {
			for (BlockPos tPos : getAllTouching(pos)) {
				if (worldIn.getBlockState(tPos).getBlock() == Blocks.WATER) {
					worldIn.setBlockState(tPos, Ice9.ice9.getDefaultState());
				}
			}
		}
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (!Config.isOnBlacklist(entityIn)) {
			if (entityIn instanceof EntityLivingBase) {
				boolean shouldKill = false;
				EntityLivingBase living = (EntityLivingBase) entityIn;
				if (living.getItemStackFromSlot(EntityEquipmentSlot.FEET).isEmpty()) {
					shouldKill = true;
				}
				if (living instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) living;
					if (player.isCreative())
						shouldKill = false;
				}
				if (shouldKill)
					living.onKillCommand();
			}
		}
	}

	private static ArrayList<BlockPos> getAllTouching(BlockPos pos) {
		ArrayList<BlockPos> list = new ArrayList<>();
		list.add(pos.up());
		list.add(pos.down());
		list.add(pos.east());
		list.add(pos.west());
		list.add(pos.north());
		list.add(pos.south());
		return list;
	}
}
