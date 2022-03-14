package com.github.gunirs.artemis.database.models;

import com.github.gunirs.artemis.api.ProductState;
import com.github.gunirs.artemis.database.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cart")
@Accessors(chain = true)
@Data
public class CartItem extends BaseModel {
    @ManyToOne(optional = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private String data;
    private int amount;
    private String imageUrl;
    private ProductState state = ProductState.INCART;
}
