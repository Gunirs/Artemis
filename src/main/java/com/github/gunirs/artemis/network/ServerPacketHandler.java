package com.github.gunirs.artemis.network;

import codechicken.lib.packet.PacketCustom;
import com.github.gunirs.artemis.api.PacketType;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.INetHandlerPlayServer;

import java.util.Optional;

public class ServerPacketHandler implements PacketCustom.IServerPacketHandler {
    @Override
    public void handlePacket(PacketCustom packetCustom, EntityPlayerMP entityPlayerMP, INetHandlerPlayServer iNetHandlerPlayServer) {
        Optional<PacketType> packetTypeOptional = PacketType.valueOf(packetCustom.getType());

        packetTypeOptional.ifPresent(packetType -> {
            switch(packetType) {
                case PRODUCT_BUY:
                    new PacketProductBuy(packetCustom.readInt(), packetCustom.readInt()).run();
                    break;
                default:
                    System.out.println("default");
            }
        });
    }
}
