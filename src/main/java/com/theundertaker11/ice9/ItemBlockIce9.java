package com.theundertaker11.ice9;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockIce9 extends ItemBlock {

	public ItemBlockIce9(Block block) {
		super(block);
		this.setRegistryName(block.getRegistryName());
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!Config.isOnBlacklist(entityIn)) {
			boolean shouldKill = false;
			if (entityIn instanceof EntityLivingBase) {
				EntityLivingBase ent = (EntityLivingBase) entityIn;
				if (ent.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Ice9.ice9)
						|| ent.getHeldItemOffhand().getItem() == Item.getItemFromBlock(Ice9.ice9)) {
					shouldKill = true;
				}
			}
			if (entityIn instanceof EntityPlayer) {
				if (((EntityPlayer) entityIn).isCreative())
					shouldKill = false;
			}
			if(!entityIn.isNonBoss())
				shouldKill = false;
			if (shouldKill)
				entityIn.onKillCommand();

		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		this.block.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add("Beware the dangers of Ice-9");
		tooltip.add("Just one touch is deadly");
	}

	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}

	@Nullable
	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack) {
		EntityItem entity = new EntityItemIce9(world, location.posX, location.posY, location.posZ, itemstack);
		if (location instanceof EntityItem) {
			NBTTagCompound tag = new NBTTagCompound();
			location.writeToNBT(tag);
			entity.setPickupDelay(tag.getShort("PickupDelay"));
		}
		entity.motionX = location.motionX;
		entity.motionY = location.motionY;
		entity.motionZ = location.motionZ;
		return entity;
	}
}
