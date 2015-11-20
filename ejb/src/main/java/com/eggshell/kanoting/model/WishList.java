package com.eggshell.kanoting.model;

import com.eggshell.kanoting.model.parents.ListEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Entity
@Table(name = "wishlist")
public class WishList extends ListEntity {

    @XmlTransient
    @ManyToMany
    // since we only want user to occur once as a member of a packlist this is needed to apply a composite unique constraint.
    @JoinTable(
            name = "wishlist_users",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "authorizedUsers_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"wishlist_id", "authorizedUsers_id" })
    )
    public List<User> authorizedUsers;
}