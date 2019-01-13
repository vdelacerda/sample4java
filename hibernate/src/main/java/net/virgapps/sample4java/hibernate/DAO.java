package net.virgapps.sample4java.hibernate;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, Id extends Serializable> {
    public T persist(T t);
    public T find(Id id);
    public List<T> findAll();
}