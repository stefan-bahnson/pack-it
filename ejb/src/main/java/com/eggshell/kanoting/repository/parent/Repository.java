package com.eggshell.kanoting.repository.parent;

import com.eggshell.kanoting.exceptions.UnauthorizedException;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.model.parents.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by tailage on 9/18/15.
 */


public abstract class Repository {

    @PersistenceContext
    private EntityManager em;

    private Logger logger;

    protected <T extends BaseEntity> T add(T entity) {
        em.persist(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    private <T extends BaseEntity> boolean isAuthorized(long id, long userId, Class<T> type) {
        String className = type.getSimpleName();
        String basePart = String.format("SELECT e FROM %s e WHERE e.id = :id", className);
        String authPart = "exists(SELECT user FROM e.authorizedUsers user WHERE user.id = :userId)";
        TypedQuery<BaseEntity> query = em.createQuery(basePart + " AND " + authPart, BaseEntity.class);
        query = query.setParameter("id", id).setParameter("userId", userId);
        return !query.getResultList().isEmpty();

    }


    public <T extends BaseEntity> T find(long id, long userId, Class<T> type) {
        if(!isAuthorized(id, userId, type)) {
            throw new UnauthorizedException();
        }
        return em.find(type, id);
    }



    protected <T extends BaseEntity> T update(long userId, T entity) {
        if(!isAuthorized(entity.id, userId, entity.getClass())) {
            throw new UnauthorizedException();
        }
        return em.merge(entity);
    }


    protected <T extends BaseEntity> void delete(long id, long userId, Class<T> type) {
        if(!isAuthorized(id, userId, type)) {
            throw new UnauthorizedException();
        }
        Object ref = em.getReference(type, id);
        em.remove(ref);

    }


    protected <T> List<T> findWithNamedQuery(String queryName) {
        return null;
    }


    protected <T> List<T> findWithNamedQuery(String queryName, int resultLimit) {
        return null;
    }


    protected <T> List<T> findWithNamedQuery(String namedQueryName, Map parameters) {
        return null;
    }


    protected <T> List<T> findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit) {
        return null;
    }

    protected EntityManager getEm() {
        return em;
    }

    protected Logger getLogger() {
        return logger;
    }
}
