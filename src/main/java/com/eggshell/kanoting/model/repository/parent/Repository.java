package com.eggshell.kanoting.model.repository.parent;

import com.eggshell.kanoting.model.entity.parents.BaseEntity;

import javax.persistence.*;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by tailage on 9/18/15.
 */

@Provider
public abstract class Repository {

    @PersistenceContext
    private EntityManager em;

    private Logger logger;

    public <T extends BaseEntity> T add(T entity) {
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

    /**
     * Finds all entities for class passed
     *
     * @param entity Entity of given type passed
     * @return Entity objects retrieved from the database
     */
    public <T> List<T> findAll(Class<T> entity) {
        return getEm().createQuery("SELECT e FROM " + entity.getName() + " e", entity).getResultList();
    }

    // fixme: auth turned off
    public <T extends BaseEntity> T find(long id, Class<T> type) {
//        if(!isAuthorized(id, userId, type)) {
//            throw new UnauthorizedException();
//        }
        return em.find(type, id);
    }


    // fixme: auth turned off
    // entity id from path param
    public <T extends BaseEntity> T update(T entity) {
//        if(!isAuthorized(entity.id, userId, entity.getClass())) {
//            throw new UnauthorizedException();
//        }

        return em.merge(entity);
    }

    // fixme: auth turned off
    public <T extends BaseEntity> void delete(long id, Class<T> type ) {
//        if(!isAuthorized(id, userId, type)) {
//            throw new UnauthorizedException();
//        }
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
