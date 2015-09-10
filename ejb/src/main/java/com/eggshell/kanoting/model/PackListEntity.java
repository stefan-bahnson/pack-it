package com.eggshell.kanoting.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "packlist")
public class PackListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(mappedBy = "packLists")
    private List<ItemEntity> items;

    @ManyToOne
    private UserEntity user;

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
