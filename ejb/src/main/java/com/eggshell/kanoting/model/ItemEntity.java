package com.eggshell.kanoting.model;

import com.eggshell.kanoting.model.enums.ItemCategory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by tailage on 9/10/15.
 */

@Entity
@Table(name = "item")
public class ItemEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    private List<PackListEntity> packLists;

    @ManyToMany
    private List<WishList> wishlists;

    @NotNull
    private String name;

    private Date created;

    private ItemCategory itemCategory;

}
