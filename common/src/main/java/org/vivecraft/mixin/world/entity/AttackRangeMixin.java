package org.vivecraft.mixin.world.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.component.AttackRange;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.phys.Vec3;

@Mixin(AttackRange.class)
public abstract class AttackRangeMixin {@ModifyReturnValue(
    method = "effectiveMaxRange",
    at = @At("RETURN")
)
private float titanVR$effectiveMaxRange(float original, Entity entity) {

    if (!(entity instanceof net.minecraft.server.level.ServerPlayer player)) {
        return original;
    }

    var vivePlayer = org.vivecraft.server.ServerVRPlayers.getVivePlayer(player);

    if (vivePlayer == null || !vivePlayer.isVR()) {
        return original;
    }


    Vec3 hand = vivePlayer.previousSuperStrengthMainHandPos;

    if (hand == null) {
        return original;
    }
    double reach = hand.distanceTo(player.position());

    return (float) Math.max(original, reach);
}

}
