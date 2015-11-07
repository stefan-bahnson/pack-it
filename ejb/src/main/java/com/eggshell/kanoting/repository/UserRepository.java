package com.eggshell.kanoting.repository;


import com.eggshell.kanoting.authentication.PasswordHashes;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.parent.Repository;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Stateless
public class UserRepository extends Repository {

    @Resource
    SessionContext ctx;

    public User findUserById(long id) {
        if(ctx.isCallerInRole("admin")) {
            System.out.println("hej");
        }
        return find(User.class, id);
    }

    public User findUserByEmail(String email) {
        return getEm().createNamedQuery(User.FIND_BY_EMAIL, User.class).setParameter("email", email).getSingleResult();
    }

    public void updateUser(User user) {
        if(getEm().getReference(User.class, user.id) != null) {
            update(user);
        }
    }

    public User addUser(User user) {
        return add(user);
    }

    public void deleteUser(User user) {
        delete(User.class, user.id);
    }

    public boolean authenticate(User user, String password) {
        try {
            return PasswordHashes.validatePassword(password, user.password);
        } catch(NoSuchAlgorithmException | InvalidKeySpecException  e) {
            throw new RuntimeException(e);
        }
    }
}