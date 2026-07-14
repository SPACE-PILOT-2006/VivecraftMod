package org.vivecraft.mixin.world.entity;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.vivecraft.server.ServerVRPlayers;
import org.vivecraft.server.ServerVivePlayer;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(method = "getDimensions", at = @At("RETURN"), cancellable = true)
    private void vivecraft$scaleSuperStrengthDimensions(
        Pose pose,
        CallbackInfoReturnable<EntityDimensions> cir
    ) {
        if ((Object) this instanceof ServerPlayer serverPlayer) {
            ServerVivePlayer vivePlayer =
                ServerVRPlayers.getVivePlayer(serverPlayer);

            if (vivePlayer != null &&
                vivePlayer.isVR() &&
                vivePlayer.superStrength) {

                float scale = Math.max(1.0F, vivePlayer.worldScale);
                EntityDimensions dimensions = cir.getReturnValue();

                cir.setReturnValue(dimensions.scale(scale));
            }
        }
    }
}
