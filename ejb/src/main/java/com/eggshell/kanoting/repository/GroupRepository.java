package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.Role;
import com.eggshell.kanoting.repository.parent.Repository;

import javax.ejb.Stateless;


@Stateless
public class GroupRepository extends Repository {

    public void addGroup(Role role) {
        add(role);
    }

    public Role findByName(String name) {
        return getEm().createNamedQuery(Role.FIND_BY_NAME, Role.class).setParameter("name", name).getSingleResult();
    }
}
