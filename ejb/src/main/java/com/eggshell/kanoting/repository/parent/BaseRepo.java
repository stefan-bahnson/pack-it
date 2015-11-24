package com.eggshell.kanoting.repository.parent;

import com.eggshell.kanoting.model.parents.BaseEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by tailage on 9/18/15.
 */

@Stateless
public class BaseRepo {

    @PersistenceContext
    protected EntityManager em;

    protected Logger logger;

    private <T extends BaseEntity> boolean isAuthorized(long id, long userId, Class<T> type) {
        String className = type.getSimpleName();
        String basePart = String.format("SELECT e FROM %s e WHERE e.id = :id", className);
        String authPart = "exists(SELECT user FROM e.authorizedUsers user WHERE user.id = :userId)";

        TypedQuery<BaseEntity> query = em.createQuery(basePart + " AND " + authPart, BaseEntity.class);
        query = query.setParameter("id", id).setParameter("userId", userId);
        return !query.getResultList().isEmpty();

    }

    public <T extends BaseEntity> T save(T entity) {
        em.persist(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }


    /**
     * Finds all entities for class passed
     *
     * @param entity Entity of given type passed
     * @return Entity objects retrieved from the database
     */
    @SuppressWarnings("unchecked")
    public List findAll(Class<?> entity) {
        return getEm().createQuery("SELECT e FROM " + entity.getName() + " e").getResultList();
    }

    // fixme: auth turned off
    public <T extends BaseEntity> T find(Class<T> type, long id) {
        return em.find(type, id);
    }


    public <T extends BaseEntity> T update(T entity) {
        return em.merge(entity);
    }

    public <T extends BaseEntity> void delete(Class<T> type, long id ) {
        Object ref = em.getReference(type, id);
        em.remove(ref);

    }

    protected EntityManager getEm() {
        return em;
    }

    protected Logger getLogger() {
        return logger;
    }


}
