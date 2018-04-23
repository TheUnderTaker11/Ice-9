package com.theundertaker11.ice9;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityItemIce9 extends EntityItem {

	public EntityItemIce9(World worldIn, double x, double y, double z, ItemStack stack) {
		super(worldIn, x, y, z, stack);
	}

	public EntityItemIce9(World worldIn, ItemStack stack) {
		super(worldIn);
		this.setItem(stack);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		IBlockState state = this.getEntityWorld().getBlockState(this.getPosition());
		if (this.isInWater() && state.getBlock() == Blocks.WATER) {
			this.getEntityWorld().setBlockState(this.getPosition(), Ice9.ice9.getDefaultState());
			this.setDead();
		}
	}
}
