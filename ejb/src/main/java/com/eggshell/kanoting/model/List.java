package com.eggshell.kanoting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@MappedSuperclass
public abstract class List extends BaseEntity {

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
    }

    @PreUpdate
    protected void onUpdated() {
        updated = new Date();
    }
}
