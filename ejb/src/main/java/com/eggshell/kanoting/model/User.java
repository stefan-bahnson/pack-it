package com.eggshell.kanoting.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String name;
    public String email;
    public String password;

    @XmlTransient
    @OneToMany(mappedBy = "user")
    public List<PackList> packLists;

    @XmlTransient
    @OneToMany(mappedBy = "user")
    public List<WishList> wishLists;

}