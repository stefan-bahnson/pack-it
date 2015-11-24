package com.eggshell.kanoting.model;

import com.eggshell.kanoting.model.parents.BaseEntity;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = User.FIND_BY_EMAIL, query = User.SELECT_FROM_WHERE + "email = :email")
})

@XmlRootElement
@Entity
@Table(name = "user")
public class User extends BaseEntity implements Principal {

    public static final String FIND_BY_EMAIL = "User.findByEmail";
    public static final String FIND_BY_ID = "User.findById";
    public static final String SELECT_FROM_WHERE = "SELECT u FROM User u WHERE u.";

    @NotNull
    public String name;

    @Email
    public String email;

    @NotNull
    public String password;

    public User() {}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @PrePersist
    public void onCreated() {
        authorizedUsers = new HashSet<>();
        authorizedUsers.add(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + password.hashCode();
        return result;
    }
}