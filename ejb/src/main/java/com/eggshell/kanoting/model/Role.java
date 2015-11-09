package com.eggshell.kanoting.model;


import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = Role.FIND_BY_NAME, query = Role.SELECT_FROM_WHERE + "name = :name")
})

@Entity
// group is a keyword in MySQL so we need to use 'faction' instead
// Daniel Laine was here
@Table(name = "role")
public class Role implements Serializable {

    public static final String FIND_BY_NAME = "Group.findByName";
    public static final String SELECT_FROM_WHERE = "SELECT g FROM Role g WHERE g.";

    public Role(String group) {
        this.name = group;
    }

    public Role() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String name;

    @Override
    public boolean equals(Object obj) {
        Role role = (Role) obj;
        return role.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }
}
