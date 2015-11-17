package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.repository.parent.Repository;
import javax.ejb.Stateless;
@Stateless
public class ItemRepository extends Repository {

    public Item findItemById(long id, long userId) {
        return find(id, userId, Item.class);
    }

    public Item addItem(Item item) {
        return add(item);
    }

    public void deleteItem(long itemId, Item item) {
        delete(itemId, Item.class);
    }
}
