package org.vivecraft.common.network.packet.c2s;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import org.vivecraft.common.network.packet.PayloadIdentifier;

public record AttackHandPayloadC2S(int entityId, InteractionHand hand)
    implements VivecraftPayloadC2S {

    @Override
    public PayloadIdentifier payloadId() {
        return PayloadIdentifier.ATTACK_HAND;
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeByte(payloadId().ordinal());
        buffer.writeVarInt(entityId);
        buffer.writeEnum(hand);
    }

    public static AttackHandPayloadC2S read(FriendlyByteBuf buffer) {
        return new AttackHandPayloadC2S(
            buffer.readVarInt(),
            buffer.readEnum(InteractionHand.class)
        );
    }
}
