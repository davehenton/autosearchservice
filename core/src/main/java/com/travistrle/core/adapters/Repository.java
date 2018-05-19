package com.travistrle.core.adapters;

import com.travistrle.core.entities.Entity;
import java.util.List;

public interface Repository<T extends Entity> {

  /**
   * Create the model to the TransactionalStore.
   *
   * @param entity {@link T}
   */
  void create(T entity);

  /**
   * Create the entities to the TransactionalStore.
   *
   * @param entities {@link Iterable}
   * @return {@link Integer}
   */
  int create(Iterable<T> entities);

  /**
   * Update the model to the TransactionalStore.
   *
   * @param entity {@link T}
   */
  void update(T entity);

  /**
   * Update the entities to the TransactionalStore.
   *
   * @param entities {@link Iterable}
   * @return {@link Integer}
   */
  int update(Iterable<T> entities);

  /**
   * Get the model to the TransactionalStore.
   *
   * @param entity {@link T}
   * @return {@link T}
   */
  T get(T entity);

  /**
   * Batch get entities.
   *
   * @param entities {@link Iterable}
   * @return {@link List}
   */
  List<T> get(final Iterable<T> entities);

  /**
   * Get all entities.
   *
   * @return {@link List}
   */
  List<T> getAll();


  /**
   * Delete an model.
   *
   * @param entity {@link T}
   */
  void delete(T entity);

  /**
   * Delete an entities.
   *
   * @param entities {@link List}
   * @return {@link Integer}
   */
  int delete(Iterable<T> entities);
}
