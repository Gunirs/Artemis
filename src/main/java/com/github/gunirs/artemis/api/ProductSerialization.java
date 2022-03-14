package com.github.gunirs.artemis.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gunirs.artemis.database.models.Product;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductSerialization {
    private static final ObjectMapper mapper = new ObjectMapper();

    private static Product getProductObjectByJson(String json) throws JsonProcessingException {
        return mapper.readValue(json, Product.class);
    }

    private static String getJsonByProductObject(Product product) throws JsonProcessingException {
        return mapper.writeValueAsString(product);
    }

    public static List<Product> getProductsByTagCompound(NBTTagCompound nbtTagCompound) {
        List<Product> products = new ArrayList<>();
        NBTTagList nbtTagList = (NBTTagList) nbtTagCompound.getTag("products");

        for(int i = 0; i < nbtTagList.tagCount(); ++i) {
            try {
                String json = nbtTagList.getStringTagAt(i);
                Product product = getProductObjectByJson(json);

                products.add(product);
            } catch(JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return products;
    }

    public static NBTTagCompound getTagCompoundByProducts(Product... products) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        NBTTagList nbtTagList = new NBTTagList();

        Arrays.stream(products).forEach(product -> {
            try {
                String json = getJsonByProductObject(product);
                nbtTagList.appendTag(new NBTTagString(json));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        nbtTagCompound.setTag("products", nbtTagList);

        return nbtTagCompound;
    }

    public static NBTTagCompound getTagCompoundByProducts(List<Product> products) {
        return getTagCompoundByProducts(products.toArray(new Product[0]));
    }
}
