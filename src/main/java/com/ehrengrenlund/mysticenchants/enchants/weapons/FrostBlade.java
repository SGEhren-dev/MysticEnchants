package com.ehrengrenlund.mysticenchants.enchants.weapons;

import com.ehrengrenlund.mysticenchants.MysticEnchants;
import com.ehrengrenlund.mysticenchants.configuration.MysticConfig;
import com.ehrengrenlund.mysticenchants.utilities.MysticHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FrostBlade extends Enchantment {
    public FrostBlade() {
        super(
                Rarity.UNCOMMON,
                EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{ EquipmentSlot.MAINHAND }
        );

        if (MysticConfig.getInstance().getFrostEnchantEnabled()) {
            Registry.register(Registries.ENCHANTMENT, new Identifier(MysticHelper.MODID, "frostblade"), this);
        }
    }

    @Override
    public int getMinPower(int level) {
        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canAccept(Enchantment enchant) {
        return super.canAccept(enchant) && enchant != Enchantments.FIRE_ASPECT && enchant != MysticEnchants.FrostBlade;
    }

    @Override
    public void onTargetDamaged(LivingEntity entity, Entity target, int level) {
        if (target instanceof LivingEntity) {
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 2 * level, level - 1));
        }

        super.onTargetDamaged(entity, target, level);
    }
}
