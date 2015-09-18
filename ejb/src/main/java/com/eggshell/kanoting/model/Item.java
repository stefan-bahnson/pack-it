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

    @ManyToMany
    @XmlTransient
    public List<PackList> packLists;

    @ManyToMany
    @XmlTransient
    public List<WishList> wishlists;

    @NotNull
    public String name;

    public Date created;

    @Enumerated(EnumType.STRING)
    public ItemCategory itemCategory;

}
