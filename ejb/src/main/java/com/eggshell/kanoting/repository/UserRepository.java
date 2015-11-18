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
import java.util.List;

@Stateless
public class UserRepository extends Repository {

    @Resource
    SessionContext ctx;

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        List<User> users = getEm().createQuery("select u from User u").getResultList();
        return users;
    }

    public User findUserById(long id, long userId) {
        return find(id, userId, User.class);
    }

    public User findUserByEmail(String email) {
        User user = null;
        try {
            user = getEm().createNamedQuery(User.FIND_BY_EMAIL, User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new NoResultException("No Users found matching email " + email); // fixme: bad idea throwing nre. return?
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
            throw new NoResultException("No Users found matching name " + name); // fixme: bad idea throwing nre. return?

        return users;
    }

    public void updateUser(long userId, User user) {
        User userRef = getEm().getReference(User.class, userId);
        user.setId(userId);

        if (userRef != null)
            update(userId, user); // userId provided to not break func for other repos but not used.
    }

    public User addUser(User user) {
        return add(user);
    }

    /*
        All we need is the id. Is there a good reason for sending an object?
    */
    public void deleteUser(long userId) {
        delete(userId, User.class);
    }

    public boolean authenticate(User user, String password) {
        try {
            return PasswordHashes.validatePassword(password, user.password);
        } catch(NoSuchAlgorithmException | InvalidKeySpecException  e) {
            throw new RuntimeException(e);
        }
    }
}