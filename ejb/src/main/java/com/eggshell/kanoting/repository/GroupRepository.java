package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.Group;
import com.eggshell.kanoting.repository.parent.Repository;

import javax.ejb.Stateless;


@Stateless
public class GroupRepository extends Repository {

    public void addGroup(Group group) {
        add(group);
    }

    public Group findByName(String name) {
        return getEm().createNamedQuery(Group.FIND_BY_NAME, Group.class).setParameter("name", name).getSingleResult();
    }
}
