package com.github.gunirs.artemis.database.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.gunirs.artemis.database.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
@Data
public class Product extends BaseModel {
    private String name;
    private String data;
    private double price;
    private double discount;
    private int minForSale;
    private String imageUrl;

    @ManyToOne
    @JsonIgnore
    private Category category;
}
