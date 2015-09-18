package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.repository.parent.Repository;

/**
 * Created by tailage on 9/18/15.
 */
public class ItemRepository extends Repository {

    public Item findItemById(long id) {
        return find(Item.class, id);
    }

    public void addItem(Item item) {
        add(item);
    }
}
