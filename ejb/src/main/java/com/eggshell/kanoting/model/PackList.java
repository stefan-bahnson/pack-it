package com.eggshell.kanoting.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "packlist")
public class PackList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(mappedBy = "packLists")
    private List<Item> items;

    @ManyToOne
    private User user;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
