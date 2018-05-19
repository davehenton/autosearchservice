package com.travistrle.core.adapters;

import com.travistrle.core.entities.Entity;
import java.util.List;

public interface Repository<T extends Entity> {

  /**
   * Create a new entity.
   *
   * @param entity {@link T}
   */
  boolean create(T entity);

  /**
   * Create list of entities.
   *
   * @param entities {@link Iterable}
   * @return {@link Integer}
   */
  int create(Iterable<T> entities);

  /**
   * Get an entity.
   *
   * @param entity {@link T}
   * @return {@link T}
   */
  T read(T entity);

  /**
   * Batch get entities.
   *
   * @param entities {@link Iterable}
   * @return {@link List}
   */
  List<T> read(final Iterable<T> entities);

  /**
   * Get all entities.
   *
   * @return {@link List}
   */
  List<T> readAll();

  /**
   * Update an existing entity.
   *
   * @param entity {@link T}
   */
  boolean update(T entity);

  /**
   * Update list of entities.
   *
   * @param entities {@link Iterable}
   * @return {@link Integer}
   */
  int update(Iterable<T> entities);


  /**
   * Delete an entity.
   *
   * @param entity {@link T}
   */
  boolean delete(T entity);

  /**
   * Delete entities.
   *
   * @param entities {@link List}
   * @return {@link Integer}
   */
  int delete(Iterable<T> entities);


}
