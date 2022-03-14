package com.github.gunirs.artemis.database.models;

import com.github.gunirs.artemis.database.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "categories")
@Data
public class Category extends BaseModel {
    private String name;
    private int sortIndex = 1000;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "category"
    )
    private List<Product> products;
}