package com.github.gunirs.artemis.network;

import com.github.gunirs.artemis.api.ProductSerialization;
import com.github.gunirs.artemis.database.models.Product;
import lombok.AllArgsConstructor;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

@AllArgsConstructor
public class PacketProductGet implements Runnable {
    private NBTTagCompound nbtTagCompound;

    @Override
    public void run() {
        List<Product> products = ProductSerialization.getProductsByTagCompound(nbtTagCompound);

        products.forEach(product -> System.out.println(product.getName()));
    }
}
