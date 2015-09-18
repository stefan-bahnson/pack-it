package com.eggshell.kanoting.model;

import com.eggshell.kanoting.model.enums.ItemCategory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    private List<PackList> packLists;

    @ManyToMany
    private List<WishList> wishlists;

    @NotNull
    private String name;

    private Date created;

    private ItemCategory itemCategory;

    public List<PackList> getPackLists() {
        return packLists;
    }

    public void setPackLists(List<PackList> packLists) {
        this.packLists = packLists;
    }

    public List<WishList> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<WishList> wishlists) {
        this.wishlists = wishlists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }
}
