package com.github.gunirs.artemis.database.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gunirs.artemis.database.models.Category;
import com.github.gunirs.artemis.database.models.Product;
import com.github.gunirs.artemis.database.models.query.QCategory;
import com.github.gunirs.artemis.database.models.query.QProduct;
import io.ebean.Database;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class ProductService {
    private Database database;

    public List<Product> getProductsByCategory(Category category) {
        return new QProduct().select(QProduct.alias().category).category.eq(category).findList();
    }

    public List<Product> getProductsByCategory(int categoryId) {
        Category category = new QCategory().select(QCategory.alias().id).id.eq(categoryId).findOne();

        return getProductsByCategory(category);
    }

    public Optional<Product> getProductById(int productId) {
        return new QProduct().select(QProduct.alias().id).id.eq(productId).findOneOrEmpty();
    }
}