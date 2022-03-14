package com.github.gunirs.artemis.proxy;

import codechicken.lib.packet.PacketCustom;
import com.github.gunirs.artemis.Artemis;
import com.github.gunirs.artemis.network.ClientPacketHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        PacketCustom.assignHandler(Artemis.MODID, new ClientPacketHandler());
    }

    @Override
    public void init(FMLInitializationEvent event) {
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
    }
}
