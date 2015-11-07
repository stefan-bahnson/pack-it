package com.eggshell.kanoting.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = User.FIND_BY_EMAIL, query = User.SELECT_FROM_WHERE + "email = :email")
})

@XmlRootElement
@Entity
@Table(name = "user")
public class User implements Serializable {

    public static final String FIND_BY_EMAIL = "User.findByEmail";
    public static final String FIND_BY_ID = "User.findById";
    public static final String SELECT_FROM_WHERE = "SELECT u FROM User u WHERE u.";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @NotNull
    public String name;


    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Group> roles;

    @Email
    public String email;

    @NotNull
    public String password;


}