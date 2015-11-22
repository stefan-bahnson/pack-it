package com.eggshell.kanoting.repository;


import com.eggshell.kanoting.authentication.PasswordHashes;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.parent.Repository;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Stateless
public class UserRepository extends Repository {

    @Resource
    SessionContext ctx;

    public User findUserByEmail(String email) {
        User user = null;
        try {
            user = getEm().createNamedQuery(User.FIND_BY_EMAIL, User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new NoResultException("No Users found matching email " + email); // fixme: bad idea throwing nre? return?
        }

        return user;
    }

    /**
     * <p>Find all users matching name param. Partial match is accepted as
     * long as the letters appear in that order and is not case sensitive.</p>
     *
     * e.g. "fr" will match both "[Fr]ed" and Al[fr]ed"
     *
     * @param name query param for name
     * @return list of {@link User}s
     */
    @SuppressWarnings("unchecked")
    public List<User> findUserByName(String name) {
        List<User> users = getEm().createQuery("select u from User u where u.name like :name")
                .setParameter("name", "%" + name + "%")
                .getResultList();
        if (users.isEmpty())
            throw new NoResultException("No Users found matching name " + name); // fixme: bad idea throwing nre? return?

        return users;
    }

    /*
        Only updates attributes that has a value. Pretty awesome!
    */
    public void updateUserByForm(long userId, String newName, String newEmail, String newPassword) {
        User user = getEm().find(User.class, userId);

        // update only attributes where new value is present
        // fixme: verbose.. optimize?
        if (newName != null) {
            user.name = newName;
        }
        if (newEmail != null) {
            user.email = newEmail;
        }
        if (newPassword !=null) {
            user.password = newPassword;
        }

        getEm().merge(user);
    }

    public boolean authenticate(User user, String password) {
        try {
            return PasswordHashes.validatePassword(password, user.password);
        } catch(NoSuchAlgorithmException | InvalidKeySpecException  e) {
            throw new RuntimeException(e);
        }
    }
}