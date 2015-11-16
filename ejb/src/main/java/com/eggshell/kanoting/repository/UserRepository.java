package com.eggshell.kanoting.repository;


import com.eggshell.kanoting.authentication.PasswordHashes;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.parent.Repository;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
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

    public User findUserById(long id, long userId) {
        return find(id, userId, User.class);
    }

    public User findUserByEmail(String email) {
        return getEm().createNamedQuery(User.FIND_BY_EMAIL, User.class).setParameter("email", email).getSingleResult();
    }

    /**
     * Find all users matching name param.
     *
     * @param name query param for name
     * @return list of {@link User}s
     */
    @SuppressWarnings("unchecked")
    public List<User> findUserByName(String name) {
        List<User> users = getEm().createQuery("select u from User u where u.name like :name")
                .setParameter("name", name)
                .getResultList();
        if (users == null)
            throw new NoResultException("No Users matching name " + name);

        return users;
    }

    public void updateUser(long userId, User user) {
        if(getEm().getReference(User.class, user.id) != null) {
            update(userId, user);
        }
    }

    public User addUser(User user) {
        return add(user);
    }

    public void deleteUser(long userId, User user) {
        delete(user.id, userId, User.class);
    }

    public boolean authenticate(User user, String password) {
        try {
            return PasswordHashes.validatePassword(password, user.password);
        } catch(NoSuchAlgorithmException | InvalidKeySpecException  e) {
            throw new RuntimeException(e);
        }
    }
}