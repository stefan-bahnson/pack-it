package com.eggshell.kanoting.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by tailage on 9/10/15.
 */

@Entity
@Table(name = "wishlist")
public class WishList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String name;

    @ManyToOne
    private UserEntity user;

    @OneToMany
    private List<ItemEntity> items;

    private Date created;

}
