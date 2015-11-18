package com.eggshell.kanoting.model.parents;

import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@XmlRootElement
@MappedSuperclass
public abstract class ListEntity extends BaseEntity {

    public String name;

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Item> items;

//    @XmlTransient
//    @NotNull
    @ManyToOne
    public User user;

    public Date created;

    public Date updated;

    @PrePersist
    protected void onCreated() {
        created = new Date();
//        authorizedUsers = new ArrayList<>();
//        authorizedUsers.add(user);
    }

    @PreUpdate
    protected void onUpdated() {
        updated = new Date();
    }
}
