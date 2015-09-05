package com.eggshell.kanoting.repository;


import com.eggshell.kanoting.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class UserRepository {

    @Inject
    private EntityManager em;

    public User findUser(int id) {
        return em.find(User.class, id);
    }

}
