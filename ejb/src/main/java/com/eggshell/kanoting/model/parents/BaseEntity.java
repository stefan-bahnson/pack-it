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
    @ManyToMany
    public List<User> authorizedUsers;
}
