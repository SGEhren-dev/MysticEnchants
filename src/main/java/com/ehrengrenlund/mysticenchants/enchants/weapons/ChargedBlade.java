package com.ehrengrenlund.mysticenchants.enchants.weapons;

import com.ehrengrenlund.mysticenchants.configuration.MysticConfig;
import com.ehrengrenlund.mysticenchants.utilities.MysticHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class ChargedBlade extends Enchantment {
    public ChargedBlade() {
        super(
                Rarity.UNCOMMON,
                EnchantmentTarget.WEAPON,
                new EquipmentSlot[]{ EquipmentSlot.MAINHAND }
        );

        if (MysticConfig.getInstance().getChargedBladeEnabled()) {
            Registry.register(Registries.ENCHANTMENT, new Identifier(MysticHelper.MODID, "chargedblade"), this);
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

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        int hitChance = MysticConfig.getInstance().getChargedBladeChance();

        if (user.getRandom().nextInt(100) <= hitChance) {
            if (target instanceof LivingEntity) {
                Vec3d position = target.getPos();
                LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(target.getEntityWorld());

                assert lightning != null;

                lightning.refreshPositionAfterTeleport(position);
                target.getEntityWorld().spawnEntity(lightning);
            }
        }

        super.onTargetDamaged(user, target, level);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof SwordItem;
    }
}
