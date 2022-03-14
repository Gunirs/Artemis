package com.github.gunirs.artemis.gui;

import com.github.gunirs.artemis.Artemis;
import com.github.gunirs.artemis.api.PacketType;
import com.github.gunirs.artemis.network.PacketProductBuy;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiShop extends GuiScreen {
    @Override
    public void initGui() {
        this.buttonList.clear();

        buttonList.add(new GuiButton(1, width / 2, height / 2, 80, 20, "BUY PRODUCT"));
    }

    @Override
    protected void actionPerformed(GuiButton guiButton) {
        if(guiButton.id == 1) {
            //Artemis.getNetworkWrapper().sendToServer(new PacketProductBuy(1, 5));
            //Artemis.createPacket(PacketType.PRODUCT_BUY).writeInt(1).writeInt(5).sendToServer();
            PacketProductBuy.create(1, 5).sendToServer();
        }
    }
}
