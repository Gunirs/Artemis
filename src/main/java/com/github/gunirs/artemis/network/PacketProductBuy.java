package com.github.gunirs.artemis.network;

import codechicken.lib.packet.PacketCustom;
import com.github.gunirs.artemis.Artemis;
import com.github.gunirs.artemis.api.PacketType;
import com.github.gunirs.artemis.database.models.Product;
import com.github.gunirs.artemis.database.models.User;
import com.github.gunirs.artemis.database.services.UserService;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class PacketProductBuy implements Runnable {
    private int productId;
    private int amount;

    @Override
    public void run() {
        UserService userService = Artemis.getUserService();
        Optional<Product> productOptional = Artemis.getProductService().getProductById(productId);
        Optional<User> userOptional = userService.getUserByName("Player20");

        if(!userOptional.isPresent()) {
            System.out.println("User not found.");
            return;
        }

        productOptional.ifPresent(product -> {
            User user = userOptional.get();

            userService.addProductToCart(user, product, amount);
            System.out.println("Successfully!");
        });
    }

    public static PacketCustom create(int productId, int amount) {
        return Artemis.createPacket(PacketType.PRODUCT_BUY)
                .writeInt(productId)
                .writeInt(amount);
    }
}
