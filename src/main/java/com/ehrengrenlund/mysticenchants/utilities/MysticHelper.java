package com.ehrengrenlund.mysticenchants.utilities;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;

public class MysticHelper {
    public static final String MODID = "mysticenchants";
    public static boolean hasEnchantment(LivingEntity entity, Enchantment enchant) {
        return EnchantmentHelper.getLevel(enchant, entity.getMainHandStack()) >= 1 || EnchantmentHelper.getLevel(enchant, entity.getOffHandStack()) >= 1;
    }

    public static int getEnchantLevel(LivingEntity entity, Enchantment enchant) {
        if (EnchantmentHelper.getLevel(enchant, entity.getMainHandStack()) >= 1) {
            return EnchantmentHelper.getLevel(enchant, entity.getMainHandStack());
        }

        if (EnchantmentHelper.getLevel(enchant, entity.getOffHandStack()) >= 1) {
            return EnchantmentHelper.getLevel(enchant, entity.getOffHandStack());
        }

        return 0;
    }
}
