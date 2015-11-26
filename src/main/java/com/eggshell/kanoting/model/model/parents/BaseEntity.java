package com.eggshell.kanoting.model.model.parents;

import com.eggshell.kanoting.model.model.User;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Set;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @XmlTransient
    @ManyToMany
    public Set<User> authorizedUsers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
