package com.eggshell.kanoting.model;

import javax.enterprise.inject.Decorated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "packlist")
public class PackList extends BaseEntity implements Serializable {

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
