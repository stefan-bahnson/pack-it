package com.eggshell.kanoting.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tailage on 9/10/15.
 */

@Entity
@Table(name = "packlist")
public class PackListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    private List<ItemEntity> items;

    @ManyToOne
    private UserEntity user;
}
