package com.github.gunirs.artemis.database.services;

import com.github.gunirs.artemis.database.models.CartItem;
import com.github.gunirs.artemis.database.models.Product;
import com.github.gunirs.artemis.database.models.User;
import com.github.gunirs.artemis.database.models.query.QUser;
import io.ebean.Database;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    private Database database;

    public Optional<User> getUserByName(String username) {
        return new QUser()
                .select(QUser.alias().name)
                .name.iequalTo(username)
                .findOneOrEmpty();
    }

    public Optional<User> getUserByUUID(String uuid) {
        return new QUser()
                .select(QUser.alias().uuid)
                .uuid.iequalTo(uuid)
                .findOneOrEmpty();
    }

    public Optional<User> getUserByUUID(UUID uuid) {
        return getUserByUUID(uuid.toString());
    }

    public void createUserIfNotExists(String username, UUID uuid) {
        Optional<User> userOptional = getUserByName(username);

        if(!userOptional.isPresent()) {
            User user = new User()
                    .setName(username)
                    .setUuid(uuid.toString());
            user.save();
        }
    }

    public void addProductToCart(User user, Product product, int amount) {
        List<CartItem> cartItems = user.getCart();
        CartItem cartItem = new CartItem()
                .setUser(user)
                .setProduct(product)
                .setData(product.getData())
                .setAmount(amount);

        cartItems.add(cartItem);

        user.setCart(cartItems);
        user.save();
    }
}
