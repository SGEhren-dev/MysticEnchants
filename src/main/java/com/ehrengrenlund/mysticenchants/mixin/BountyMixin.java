package com.ehrengrenlund.mysticenchants.mixin;

import com.ehrengrenlund.mysticenchants.MysticEnchants;
import com.ehrengrenlund.mysticenchants.configuration.MysticConfig;
import com.ehrengrenlund.mysticenchants.utilities.MysticHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class BountyMixin extends Entity {
    @Shadow public abstract int getXpToDrop();

    protected BountyMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "onDeath")
    private void experienceKill(DamageSource source, CallbackInfo ci) {
        if ((source.getAttacker() instanceof PlayerEntity player) && this.getXpToDrop() > 0) {
            if (MysticHelper.hasEnchantment(player, MysticEnchants.BountyHunter)) {
                int enchantLevel = MysticHelper.getEnchantLevel(player, MysticEnchants.BountyHunter);
                player.addExperience(enchantLevel * MysticConfig.getInstance().getBountyBaseExperience());
            }
        }
    }
}
