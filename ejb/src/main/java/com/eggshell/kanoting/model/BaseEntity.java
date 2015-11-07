package com.eggshell.kanoting.model;

import javax.persistence.*;
import java.util.List;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToMany(fetch = FetchType.EAGER)
    public List<User> authorizedUsers;
}
