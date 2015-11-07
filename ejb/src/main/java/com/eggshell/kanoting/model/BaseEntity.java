package com.eggshell.kanoting.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToMany(fetch = FetchType.EAGER)
    public List<User> authorizedUsers;
}
