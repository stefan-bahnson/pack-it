package com.eggshell.kanoting.repository.parent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by tailage on 9/18/15.
 */


public abstract class Repository {

    @PersistenceContext
    EntityManager em;

    private Logger logger;

    protected <T> T add(T t) {
        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }

    @SuppressWarnings("unchecked")
    protected <T> T find(Class type, Object id) {
        return (T) this.em.find(type, id);
    }


    protected <T> T update(T t) {
        return this.em.merge(t);
    }


    protected void delete(Object t) {
        Object ref = this.em.getReference(t.getClass(), t);
        this.em.remove(t);
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
