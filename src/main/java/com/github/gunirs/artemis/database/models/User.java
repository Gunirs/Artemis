package com.github.gunirs.artemis.database.models;

import com.github.gunirs.artemis.database.BaseModel;
import io.ebean.annotation.Length;
import io.ebean.annotation.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Accessors(chain = true)
@Data
public class User extends BaseModel {
    @NotNull
    @Length(32)
    private String name;

    @NotNull
    @Length(36)
    private String uuid;

    private double balance;

    @OneToMany(mappedBy = "user")
    private List<CartItem> cart;
}
