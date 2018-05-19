package com.travistrle.core.usecases;

import com.travistrle.core.entities.Entity;
import java.util.List;

public interface EntityManager<T extends Entity> {

  /**
   * Create a new entity.
   *
   * @param entity {@link T}
   */
  void create(T entity);

  /**
   * Get an entity.
   *
   * @param entity {@link T}
   * @return {@link T}
   */
  T read(T entity);

  /**
   * Update an existing entity.
   *
   * @param entity {@link T}
   */
  void update(T entity);


  /**
   * Delete an entity.
   *
   * @param entity {@link T}
   */
  void delete(T entity);

  /**
   * Delete entities.
   *
   * @param entities {@link List}
   * @return {@link Integer}
   */
  int delete(Iterable<T> entities);
}
