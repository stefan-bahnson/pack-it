package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.model.PackList;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.parent.Repository;

import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Stateless
public class PackListRepository extends Repository {


    public PackList findPackListById(long packlistId) {
        PackList packlist = find(packlistId, PackList.class);
        if (packlist == null)
            // fixme: can not find connection to mapper
            throw new EntityNotFoundException("No packlist with id " + packlistId + " could be found");

        return packlist;
    }

    public void addPackList(PackList packList) {
        try {
            add(packList); // can throw hibernate ConstraintViolationEx
        } catch (PersistenceException ex) {
            System.out.println("Can not persist packlist for unknown user with id " + packList.user.id);
        }
    }

    public void updatePackList(PackList packList) {
        update(packList);
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

    public void addUserToPacklist(long userId, long packlistId) {
        PackList packlist = getEm().find(PackList.class, packlistId);
        User user = getEm().find(User.class, userId);
        if (packlist == null || user == null)
            throw new EntityNotFoundException("Failed to add user to packlist! Either user or packlist does not exist.");
        else {
            packlist.authorizedUsers.add(user);
        }

        try {
            getEm().merge(packlist);
        } catch (PersistenceException ex) {
            System.out.println(ex.getMessage());;
        }
    }

    public void addUsersToPacklist(List<User> users, long packlistId) {
        PackList packlist = getEm().find(PackList.class, packlistId);
        users.forEach(packlist.authorizedUsers::add);
        getEm().merge(packlist);
    }

    @SuppressWarnings("unchecked")
    public List<PackList> findPacklistsByAuthUserId(long authUserId) {
        List<PackList> packlists = getEm().createQuery("select p from PackList p join p.authorizedUsers pu where pu.id = :id")
                .setParameter("id", authUserId)
                .getResultList();

        return packlists;
    }
}
