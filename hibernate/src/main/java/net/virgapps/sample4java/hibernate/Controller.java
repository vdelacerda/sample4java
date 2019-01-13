package net.virgapps.sample4java.hibernate;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;

public class Controller<T, Id extends Serializable> implements DAO<T, Id> {
	private final Class<T> clazz;
	private EntityManager entityManager;
    
    protected Controller(Class<T> clazz) {
        this.clazz = clazz;
    }
    
    public EntityManager createEntityManager() {
    	if (entityManager == null) {
    		// The name match the name in persistence.xml
    		entityManager = Persistence.createEntityManagerFactory("net.virgapps.sample4java.hibernate.jpa").createEntityManager();
    	}
    	return entityManager;
    }

	@Override
	public T persist(T t) {
		T result = null;
		EntityManager em = createEntityManager();
		try {
			em.getTransaction().begin();
			result = (T) em.merge(t);
			em.getTransaction().commit();		
		} catch (HibernateException e) {
			if (em.getTransaction() != null)
				em.getTransaction().rollback();
		}
        return result;
	}

	@Override
	public T find(Id id) {
		T t = null;
		EntityManager em = createEntityManager();
		try {
			em.getTransaction().begin();
	        t = (T) em.find(clazz, id);
		} catch (HibernateException e) {
			if (em.getTransaction() != null)
				em.getTransaction().rollback();
		}
        return t;
	}

	@Override
	public List<T> findAll() {
		List<T> list = Collections.emptyList();
		EntityManager em = createEntityManager();
		em = createEntityManager();
		try {
			em.getTransaction().begin();
	        list = (List<T>) em.createQuery("from " + clazz.getName(), clazz).getResultList();
		}
		catch (HibernateException e) {
			if (em.getTransaction() != null)
				em.getTransaction().rollback();
		}
        return list;
	}
}