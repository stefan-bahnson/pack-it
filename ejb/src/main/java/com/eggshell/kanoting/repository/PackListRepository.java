package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.data.User;
import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.model.PackList;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.parent.Repository;
import javax.ejb.Stateless;
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

    public void deleteItemsFromPackList(PackList packList) {

        PackList attachedPacklist = findPackListById(packList.id);
        Stream<Item> filteredItems = packList.items.stream()
                .filter(attachedPacklist.items::contains);

        attachedPacklist.items = filteredItems
                .collect(Collectors.toSet());
    }
}
