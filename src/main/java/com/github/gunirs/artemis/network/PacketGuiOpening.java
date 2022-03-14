package com.github.gunirs.artemis.network;

import com.github.gunirs.artemis.api.GuiType;
import com.github.gunirs.artemis.gui.GuiShop;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.minecraft.client.Minecraft;

@NoArgsConstructor
@AllArgsConstructor
public class PacketGuiOpening implements IMessage {
    private GuiType guiType;

    public static class Handler implements IMessageHandler<PacketGuiOpening, IMessage> {

        @Override
        public IMessage onMessage(PacketGuiOpening message, MessageContext ctx) {
            GuiType guiType = message.guiType;

            switch(guiType) {
                case SHOP:
                    Minecraft.getMinecraft().displayGuiScreen(new GuiShop());
                    break;
                default:
                    System.out.println("Undefined GuiType=[" + guiType + "]");
            }
            return null;
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(guiType.ordinal());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.guiType = GuiType.valueOf(buf.readInt()).orElse(GuiType.NONE);
    }
}
