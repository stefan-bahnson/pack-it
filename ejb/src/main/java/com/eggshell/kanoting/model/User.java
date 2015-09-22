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
    private long id;

    private String name;
    private String email;
    private String password;

    @XmlTransient
    @OneToMany(mappedBy = "user")
    private List<PackList> packLists;

    @XmlTransient
    @OneToMany(mappedBy = "user")
    private List<WishList> wishLists;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PackList> getPackLists() {
        return packLists;
    }

    public void setPackLists(List<PackList> packLists) {
        this.packLists = packLists;
    }

    public List<WishList> getWishLists() {
        return wishLists;
    }

    public void setWishLists(List<WishList> wishLists) {
        this.wishLists = wishLists;
    }
}