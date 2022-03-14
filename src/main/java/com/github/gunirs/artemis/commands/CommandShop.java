package com.github.gunirs.artemis.commands;

import codechicken.lib.packet.PacketCustom;
import com.github.gunirs.artemis.Artemis;
import com.github.gunirs.artemis.api.GuiType;
import com.github.gunirs.artemis.api.PacketType;
import com.github.gunirs.artemis.api.ProductSerialization;
import com.github.gunirs.artemis.database.models.Product;
import com.github.gunirs.artemis.network.PacketGuiOpening;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Collections;
import java.util.List;

public class CommandShop implements ICommand {
    @Override
    public String getCommandName() {
        return "shop";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "/shop";
    }

    @Override
    public List getCommandAliases() {
        return Collections.emptyList();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        List<Product> productList = Artemis.getProductService().getProductsByCategory(1);
        NBTTagCompound nbtTagCompound = ProductSerialization.getTagCompoundByProducts(productList);
        Artemis.createPacket(PacketType.PRODUCT_GETTING).writeNBTTagCompound(nbtTagCompound).sendToPlayer((EntityPlayer) sender);
        Artemis.createPacket(PacketType.GUI_OPEN).writeInt(GuiType.SHOP.ordinal()).sendToPlayer((EntityPlayer) sender);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
