package com.eggshell.kanoting.model.parents;

import com.eggshell.kanoting.model.User;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @XmlTransient
    // since we only want user to occur once as a member of a packlist this is needed to apply a composite unique constraint.
    @JoinTable(
            name = "packlist_users",
            joinColumns = @JoinColumn(name = "packlist_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"packlist_id", "user_id" })
    )
    @ManyToMany
    public List<User> authorizedUsers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
