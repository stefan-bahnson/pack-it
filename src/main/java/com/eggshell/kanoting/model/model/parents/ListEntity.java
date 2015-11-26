package com.eggshell.kanoting.model.model.parents;

import com.eggshell.kanoting.model.model.Item;
import com.eggshell.kanoting.model.model.User;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
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
//        authorizedUsers.save(user);
    }

    @PreUpdate
    protected void onUpdated() {
        updated = new Date();
    }
}
