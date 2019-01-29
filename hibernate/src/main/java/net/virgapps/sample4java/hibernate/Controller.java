package net.virgapps.sample4java.hibernate;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;

public class Controller<T, Id extends Serializable> implements DAO<T, Id> {
	private final Class<T> clazz;
	private static EntityManager entityManager;
    
    protected Controller(Class<T> clazz) {
        this.clazz = clazz;
    }
    
    public EntityManager getEntityManager() {
    	if (entityManager == null) {
    		// The name match the name in persistence.xml
    		entityManager = Persistence.createEntityManagerFactory("net.virgapps.sample4java.hibernate.jpa").createEntityManager();
    	}
    	return entityManager;
    }
    

	@Override
	public T persist(T t) {
		T result = null;
		try {
			getEntityManager().getTransaction().begin();
			result = (T) getEntityManager().merge(t);
			getEntityManager().getTransaction().commit();		
		} catch (HibernateException e) {
			if (getEntityManager().getTransaction() != null)
				getEntityManager().getTransaction().rollback();
		}
        return result;
	}

	@Override
	public T find(Id id) {
		T t = null;
		try {
			getEntityManager().getTransaction().begin();
	        t = (T) getEntityManager().find(clazz, id);
		} catch (HibernateException e) {
			if (getEntityManager().getTransaction() != null)
				getEntityManager().getTransaction().rollback();
		}
        return t;
	}

	@Override
	public List<T> findAll() {
		List<T> list = Collections.emptyList();
		try {
	        list = (List<T>) getEntityManager().createQuery("from " + clazz.getName(), clazz).getResultList();
		}
		catch (HibernateException e) {
			
		}
        return list;
	}
}