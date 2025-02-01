package edu.icet.clothify.repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T> extends SuperDao{
    boolean save(T t) ;
    boolean delete(Integer t);
    T get(String id);
    boolean update(T t);
    List<T> getAll();
}
