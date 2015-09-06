package com.eggshell.kanoting.repository;


import com.eggshell.kanoting.model.UserEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class UserRepository {

    @Inject
    private EntityManager em;

    public UserEntity findUser(long id) {
        return em.find(UserEntity.class, id);
    }

    public void addUser(UserEntity userEntity) {
        em.persist(userEntity);
    }

}