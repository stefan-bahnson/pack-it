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
    public long id;

    @NotNull
    public String name;

    public Date created;

    public Date updated;

    @ManyToMany(mappedBy = "items")
    @XmlTransient
    public List<PackList> packLists;

    @ManyToMany(mappedBy = "items")
    @XmlTransient
    public List<WishList> wishlists;

    @Enumerated(EnumType.STRING)
    public ItemCategory itemCategory;

    @PrePersist
    public void onCreated() {
        created = new Date();
    }

    @PreUpdate
    public void onUpdated() {
        updated = new Date();
    }

}
