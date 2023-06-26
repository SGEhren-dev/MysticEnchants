package com.ehrengrenlund.mysticenchants;

import com.ehrengrenlund.mysticenchants.enchants.weapons.BountyHunter;
import com.ehrengrenlund.mysticenchants.enchants.weapons.ChargedBlade;
import com.ehrengrenlund.mysticenchants.enchants.weapons.FrostBlade;
import com.ehrengrenlund.mysticenchants.enchants.weapons.ToxicBlade;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;

public class MysticEnchants implements ModInitializer {
	public static Enchantment BountyHunter;
	public static Enchantment FrostBlade;
	public static Enchantment ToxicBlade;
	public static Enchantment ChargedBlade;

	@Override
	public void onInitialize() {
		BountyHunter = new BountyHunter();
		FrostBlade = new FrostBlade();
		ToxicBlade = new ToxicBlade();
		ChargedBlade = new ChargedBlade();
	}
}