package com.github.gunirs.artemis.network;

import codechicken.lib.packet.PacketCustom;
import com.github.gunirs.artemis.api.GuiType;
import com.github.gunirs.artemis.api.PacketType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.INetHandlerPlayClient;

import java.util.Optional;

public class ClientPacketHandler implements PacketCustom.IClientPacketHandler {
    @Override
    public void handlePacket(PacketCustom packetCustom, Minecraft minecraft, INetHandlerPlayClient iNetHandlerPlayClient) {
        Optional<PacketType> packetTypeOptional = PacketType.valueOf(packetCustom.getType());

        packetTypeOptional.ifPresent(packetType -> {
            switch(packetType) {
                case PRODUCT_GETTING:
                    new PacketProductGet(packetCustom.readNBTTagCompound()).run();
                    break;
                case GUI_OPEN:
                    new PacketGuiOpen(GuiType.valueOf(packetCustom.readInt()).orElse(GuiType.NONE)).run();
                    break;
                default:
                    System.out.println("default");
            }
        });
    }
}
