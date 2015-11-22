package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.Item;
import com.eggshell.kanoting.model.PacklList;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.parent.Repository;

import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class PackListRepository extends Repository {


    public void deleteItemsFromPackList(long id, List<Item> items) {

        PacklList attachedPacklList = getEm().find(PacklList.class, id);

        Stream<Item> filteredItems = items.stream()
                .filter(attachedPacklList.items::contains);

        attachedPacklList.items = filteredItems
                .collect(Collectors.toSet());
    }

    public List<PacklList> findPackListsByUserId(long userId) {
        List<PacklList> foundPls = getEm().createNamedQuery("PackList.findPackListByUserId", PacklList.class)
                .setParameter("userId", userId)
                .getResultList();
        if (foundPls.isEmpty())
            throw new EntityNotFoundException("No pack lists for user " + userId + " could be found");

        return foundPls;
    }

    public void addUserToPacklist(long userId, long packlistId) {
        PacklList packlList = getEm().find(PacklList.class, packlistId);
        User user = getEm().find(User.class, userId);
        if (packlList == null || user == null)
            throw new EntityNotFoundException("Failed to add user to packlist! Either user or packlist does not exist.");
        else {
            packlList.authorizedUsers.add(user);
        }

        try {
            getEm().merge(packlList);
        } catch (PersistenceException ex) {
            System.out.println(ex.getMessage());;
        }
    }

    public void addUsersToPacklist(List<User> users, long packlistId) {
        PacklList packlList = getEm().find(PacklList.class, packlistId);

        // something broke. getReference solved it
        for (User u : users) {
            User userRef = getEm().getReference(User.class, u.id);
            packlList.authorizedUsers.add(userRef);
        }

        getEm().merge(packlList);
    }

    @SuppressWarnings("unchecked")
    public List<PacklList> findPacklistsByAuthUserId(long authUserId) {
        return getEm().createQuery("select p from PacklList p join p.authorizedUsers pu where pu.id = :id")
                .setParameter("id", authUserId)
                .getResultList();
    }
}
