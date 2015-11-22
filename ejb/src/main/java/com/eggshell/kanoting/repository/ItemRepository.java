package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.repository.parent.Repository;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Stateless
public class ItemRepository extends Repository {

    public Item findItemById(long itemId) {
        return find(itemId, Item.class);
    }

    public Item addItem(Item item) {
        return add(item);
    }

    public void deleteItem(Item item) {
        delete(item.id, Item.class);
    }

    public List<Item> findAll() {
        List<Item> items = getEm().createQuery("select i from Item i").getResultList();
        if (items.isEmpty())
            throw new EntityNotFoundException("No items found");

        return items;

    }

    public void updateItem(long itemId, Item item) {
        Item itemRef = getEm().getReference(Item.class, itemId);
        item.setId(itemId);

        if (itemRef != null) //fixme: cant't check proxy for null. impl better.
            update(item);
    }
}