package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.model.PackList;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.parent.Repository;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Stateless
public class PackListRepository extends Repository {


    public PackList findPackListById(long packlistId) {
        return find(packlistId, PackList.class);
    }

    public void addPackList(PackList packList) {
        try {
            add(packList); // can throw hibernate ConstraintViolationEx
        } catch (PersistenceException ex) {
            System.out.println("Can not persist packlist for unknown user with id " + packList.user.id);
        }
    }

    public void updatePackList(long userId, PackList packList) {
        update(userId, packList);
    }

    /*
        again, why do we need to send an object?
    */
    public void deletePacklist(long packlistId) {
        delete(packlistId, PackList.class);
    }

    public void deleteItemsFromPackList(long id, List<Item> items) {

        PackList attachedPacklist = findPackListById(id);

        Stream<Item> filteredItems = items.stream()
                .filter(attachedPacklist.items::contains);

        attachedPacklist.items = filteredItems
                .collect(Collectors.toSet());
    }

    public List<PackList> findPackListsByUserId(long userId) {
        List<PackList> foundPls = getEm().createNamedQuery("PackList.findPackListByUserId", PackList.class)
                .setParameter("userId", userId)
                .getResultList();
        if (foundPls.isEmpty())
            throw new EntityNotFoundException("No pack lists for user " + userId + " could be found");

        return foundPls;
    }

    public List<PackList> findAll() {
        List<PackList> foundPls = getEm().createNamedQuery("PackList.findALL", PackList.class).getResultList();
        if (foundPls.isEmpty())
            throw new EntityNotFoundException("No packlists found");

        return foundPls;
    }
}
