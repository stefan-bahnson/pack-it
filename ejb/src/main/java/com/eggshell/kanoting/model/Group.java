package com.eggshell.kanoting.model;


import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = Group.FIND_BY_NAME, query = Group.SELECT_FROM_WHERE + "name = :name")
})

@Entity
// group is a keyword in MySQL so we need to use 'faction' instead
@Table(name = "faction")
public class Group implements Serializable {

    public static final String FIND_BY_NAME = "Group.findByName";
    public static final String SELECT_FROM_WHERE = "SELECT g FROM Group g WHERE g.";

    public Group(String group) {
        this.name = group;
    }

    public Group() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String name;
}
