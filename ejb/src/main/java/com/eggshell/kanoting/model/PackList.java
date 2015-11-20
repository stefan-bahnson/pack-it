package com.eggshell.kanoting.model;

import com.eggshell.kanoting.model.parents.ListEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@NamedQueries({
    @NamedQuery(name = "PackList.findALL",
            query = "SELECT pl FROM PackList pl"),
    @NamedQuery(name = "PackList.findPackListByUserId",
            query = "SELECT pl FROM PackList pl WHERE pl.user.id = :userId"),
    @NamedQuery(name="PackList.findByUsers",
            query="Select p FROM PackList p WHERE p.user.id = :userId")
})
@Entity
@Table(name = "packlist")
public class PackList extends ListEntity {

    @XmlTransient
    @ManyToMany
    // since we only want user to occur once as a member of a packlist this is needed to apply a composite unique constraint.
    @JoinTable(
            name = "packlist_users",
            joinColumns = @JoinColumn(name = "packlist_id"),
            inverseJoinColumns = @JoinColumn(name = "authorizedUsers_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"packlist_id", "authorizedUsers_id" })
    )
    public List<User> authorizedUsers;
}