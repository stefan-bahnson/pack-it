package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.model.PackList;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.parent.Repository;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
// Daniel Laine was here
@Stateless
public class PackListRepository extends Repository {


    public PackList findPackListById(long id, long userId) {
        return find(id, userId, PackList.class);
    }

    public void addPackList(PackList packList) {
        User userForAdd = getEm().find(User.class, packList.user.id);
        if(userForAdd != null) {
            add(packList);
        }
    }

    public void updatePackList(long userId, PackList packList) {
        update(userId, packList);
    }

    public void deletePacklist(long userId, PackList packList) {
        delete(packList.id, userId, PackList.class);
    }

    public void deleteItemsFromPackList(long id, long userId, List<Item> items) {

        PackList attachedPacklist = findPackListById(id, userId);

        Stream<Item> filteredItems = items.stream()
                .filter(attachedPacklist.items::contains);

        attachedPacklist.items = filteredItems
                .collect(Collectors.toSet());
    }
}
