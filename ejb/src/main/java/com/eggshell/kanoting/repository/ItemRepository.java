package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.repository.parent.Repository;
import javax.ejb.Stateless;


/**
 * Created by tailage on 9/18/15.
 */

@Stateless
public class ItemRepository extends Repository {

    public Item findItemById(long id) {
        return find(Item.class, id);
    }

    public Item addItem(Item item) {
        return add(item);
    }

    public void deleteItem(Item item) {
        delete(Item.class, item.id);
    }
}
