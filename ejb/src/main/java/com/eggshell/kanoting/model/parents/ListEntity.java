package com.eggshell.kanoting.model.parents;

import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@MappedSuperclass
public abstract class ListEntity extends BaseEntity {

    public String name;

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Item> items;

    @NotNull
    @ManyToOne
    public User user;

    public Date created;

    public Date updated;

    @PrePersist
    protected void onCreated() {
        created = new Date();
        authorizedUsers = new ArrayList<>();
        authorizedUsers.add(user);
    }

    @PreUpdate
    protected void onUpdated() {
        updated = new Date();
    }
}
