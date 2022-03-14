package com.github.gunirs.artemis.network;

import com.github.gunirs.artemis.api.GuiType;
import com.github.gunirs.artemis.gui.GuiShop;
import lombok.AllArgsConstructor;
import net.minecraft.client.Minecraft;

@AllArgsConstructor
public class PacketGuiOpen implements Runnable {
    private GuiType guiType;

    @Override
    public void run() {
        switch(guiType) {
            case SHOP:
                Minecraft.getMinecraft().displayGuiScreen(new GuiShop());
                break;
            default:
                System.out.println("Undefined GuiType=[" + guiType + "]");
        }
    }
}
