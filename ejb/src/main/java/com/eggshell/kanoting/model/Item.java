package com.eggshell.kanoting.model;

import com.eggshell.kanoting.model.enums.ItemCategory;
import com.eggshell.kanoting.model.parents.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "item")
public class Item extends BaseEntity {

    @NotNull
    public String name;

    public Date created;

    public Date updated;

    @Enumerated(EnumType.STRING)
    public ItemCategory itemCategory;

    @XmlTransient
    @ManyToMany
    // since we only want user to occur once as a member of a packlist this is needed to apply a composite unique constraint.
    @JoinTable(
            name = "item_users",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "authorizedUsers_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"item_id", "authorizedUsers_id" })
    )
    public List<User> authorizedUsers;

    @PrePersist
    private void onCreated() {
        created = new Date();
    }

    @PreUpdate
    private void onUpdated() {
        updated = new Date();
    }

}
