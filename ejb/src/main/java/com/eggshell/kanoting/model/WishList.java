package com.eggshell.kanoting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "wishlist")
public class WishList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String name;

    @NotNull
    @ManyToOne
    public User user;

    @ManyToMany(fetch = FetchType.EAGER)
    public List<Item> items;

    public Date created;

    public Date updated;


}
