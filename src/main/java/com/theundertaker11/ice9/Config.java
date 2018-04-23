package com.theundertaker11.ice9;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraftforge.common.config.Configuration;

public class Config {

	public static ArrayList<String> entityBlacklist;
	public static boolean fastmode;
	
	public static void init(Configuration config) {
		config.load();
		String[] array = { "EntitySkeleton", "EntityBlaze", "EntityEnderman", "EntityEndermite", "EntityGolem" };
		entityBlacklist = new ArrayList(Arrays.asList(config.getStringList("Entity to kill blacklist", "Blacklist", array, "Use the entity class names, as shown here. Any entities added will not be killed by ice9")));
		fastmode = config.getBoolean("Fastmode", "General", true, "Fast mode very quickly turns an ocean into Ice-9, but slowmode can be better on performance");
		config.save();
	}

	public static boolean isOnBlacklist(Entity entity) {
		for (String s : entityBlacklist) {
			String entName = entity.getClass().getSimpleName();
			if(s.equals(entName))
				return true;
		}
		return false;
	}
}
