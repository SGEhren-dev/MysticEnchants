package com.ehrengrenlund.mysticenchants.enchants.weapons;

import com.ehrengrenlund.mysticenchants.configuration.MysticConfig;
import com.ehrengrenlund.mysticenchants.utilities.MysticHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BountyHunter extends Enchantment {
    public BountyHunter() {
        super(
                Rarity.UNCOMMON,
                EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{ EquipmentSlot.MAINHAND }
        );

        if (MysticConfig.getInstance().getBountyEnchantEnabled()) {
            Registry.register(Registries.ENCHANTMENT, new Identifier(MysticHelper.MODID, "bountyhunter"), this);
        }
    }

    @Override
    public int getMinPower(int level) {
        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}
