package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.model.PackList;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.parent.Repository;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class PackListRepository extends Repository {


    public PackList findPackListById(long id) {
        return find(PackList.class, id);
    }

    public void addPackList(PackList packList) {
        User userForAdd = getEm().find(User.class, packList.user.id);
        if(userForAdd != null) {
            add(packList);
        }
    }

    public void updatePackList(PackList packList) {
        update(packList);
    }

    public void deletePacklist(PackList packList) {
        delete(PackList.class, packList.id);
    }

    public void deleteItemsFromPackList(long id, List<Item> items) {

        PackList attachedPacklist = findPackListById(id);

        Stream<Item> filteredItems = items.stream()
                .filter(attachedPacklist.items::contains);

        attachedPacklist.items = filteredItems
                .collect(Collectors.toSet());
    }
}
