package org.vivecraft.common.network.packet.c2s;

import net.minecraft.network.FriendlyByteBuf;
import org.vivecraft.common.network.packet.PayloadIdentifier;

public record SuperStrengthWeaponsPayloadC2S(boolean enabled) implements VivecraftPayloadC2S {

    @Override
    public PayloadIdentifier payloadId() {
        return PayloadIdentifier.SUPER_STRENGTH_WEAPONS;
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeByte(payloadId().ordinal());
        buffer.writeBoolean(this.enabled);
    }

    public static SuperStrengthWeaponsPayloadC2S read(FriendlyByteBuf buffer) {
        return new SuperStrengthWeaponsPayloadC2S(buffer.readBoolean());
    }
}
