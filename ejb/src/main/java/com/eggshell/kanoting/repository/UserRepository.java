package com.eggshell.kanoting.repository;


import com.eggshell.kanoting.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class UserRepository {

    @Inject
    private EntityManager em;

    public User findUser(long id) {
        return em.find(User.class, id);
    }

    public void addUser(User user) {
        em.persist(user);
    }

}