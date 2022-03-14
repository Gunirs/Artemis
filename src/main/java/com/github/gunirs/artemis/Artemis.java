package com.github.gunirs.artemis;

import codechicken.lib.packet.PacketCustom;
import com.github.gunirs.artemis.api.PacketType;
import com.github.gunirs.artemis.commands.CommandShop;
import com.github.gunirs.artemis.database.EbeanServer;
import com.github.gunirs.artemis.database.services.ProductService;
import com.github.gunirs.artemis.database.services.UserService;
import com.github.gunirs.artemis.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Artemis.MODID, name = Artemis.MODNAME, version = Artemis.MODVERSION)
public class Artemis {
    public static final String MODID = "artemis";
    public static final String MODNAME = "Artemis";
    public static final String MODVERSION = "1.0-1.7.10";

    @SidedProxy(
            clientSide = "com.github.gunirs.artemis.proxy.ClientProxy",
            serverSide = "com.github.gunirs.artemis.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    private static UserService userService;
    private static ProductService productService;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if(event.getSide().isClient()) {
            userService = new UserService();
            productService = new ProductService();
        } else {
            EbeanServer ebeanServer = new EbeanServer();
            userService = new UserService(ebeanServer.getDatabase());
            productService = new ProductService(ebeanServer.getDatabase());
        }

        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandShop());
    }

    public static UserService getUserService() {
        return userService;
    }

    public static ProductService getProductService() {
        return productService;
    }

/*
    public static void registerNetworkMessages() {

        Artemis.getNetworkWrapper().registerMessage(PacketProductBuy.Handler.class, PacketProductBuy.class, 0, Side.SERVER);
        networkWrapper.registerMessage(PacketProductsSendingToClient.Handler.class, PacketProductsSendingToClient.class, 0, Side.CLIENT);
        networkWrapper.registerMessage(PacketGuiOpening.Handler.class, PacketGuiOpening.class, 1, Side.CLIENT);
    }
*/

    public static PacketCustom createPacket(PacketType packetType) {
        return new PacketCustom(MODID, packetType.ordinal());
    }
}
