package com.theundertaker11.ice9;

import org.apache.logging.log4j.Logger;

import com.theundertaker11.ice9.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(modid = Reference.MODID, version = Reference.VERSION, name = Reference.NAME, acceptedMinecraftVersions = Reference.ACCEPTED_MINECRAFT)
public class Ice9 {
	
	public static Block ice9 = new BlockIce9("ice9");
	
	@SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.SERVERPROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		Config.init(config);
		register(ice9, new ItemBlockIce9(ice9));
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}

	private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		ForgeRegistries.BLOCKS.register(block);
		if (itemBlock != null) {
			ForgeRegistries.ITEMS.register(itemBlock);
		}
		proxy.registerItemRenderer2(itemBlock, 0, "ice");
		return block;
	}

	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}
}
