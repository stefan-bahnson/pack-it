package com.eggshell.kanoting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "packlist")
public class PackList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String name;

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Item> items;

    @NotNull
    @ManyToOne
    public User user;

    public Date created;

    public Date updated;

    @PrePersist
    private void onCreated() {
        created = new Date();
    }

    @PreUpdate
    private void onUpdated() {
        updated = new Date();
    }

}
