package com.eggshell.kanoting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "packlist")
public class PackList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String name;

    @ManyToMany(fetch = FetchType.EAGER)
    public List<Item> items;

    @NotNull
    @ManyToOne
    public User user;

    public Date created;

    public Date updated;
    
    @PrePersist
    public void onCreated() {
        created = new Date();
    }

    @PreUpdate
    public void onUpdated() {
        updated = new Date();
    }

}
