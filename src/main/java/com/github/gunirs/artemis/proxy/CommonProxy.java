package com.github.gunirs.artemis.proxy;

import codechicken.lib.packet.PacketCustom;
import com.github.gunirs.artemis.Artemis;
import com.github.gunirs.artemis.listeners.ConnectionEvent;
import com.github.gunirs.artemis.network.ServerPacketHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        PacketCustom.assignHandler(Artemis.MODID, new ServerPacketHandler());

        FMLCommonHandler.instance().bus().register(new ConnectionEvent());
        MinecraftForge.EVENT_BUS.register(new ConnectionEvent());
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {
    }
}