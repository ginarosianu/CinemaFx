package Repository;

import Domain.Entity;

import java.util.List;

public interface IRepository <T extends Entity > {
    /**
     * returns an entity by id
     *
     * @param id
     * @return an entity;
     */
    T getById(String id);

    /**
     * adds a movie
     *
     * @param entity
     */
    void insert(T entity);

    /**
     * updates an entity;
     *
     * @param entity
     */
    void update(T entity);

    /**
     * deletes an entity;
     *
     * @param id
     */
    void remove(String id);

    List<T> getAll();
}