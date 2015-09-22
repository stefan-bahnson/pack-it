package com.eggshell.kanoting.model;

import com.eggshell.kanoting.model.enums.ItemCategory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    private Date created;

    private Date updated;

    @ManyToMany(mappedBy = "items")
    @XmlTransient
    private List<PackList> packLists;

    @ManyToMany(mappedBy = "items")
    @XmlTransient
    private List<WishList> wishlists;

    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;

    @PrePersist
    private void onCreated() {
        created = new Date();
    }

    @PreUpdate
    private void onUpdated() {
        updated = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

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

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }
}
