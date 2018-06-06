package com.travistrle.core.usecases.user;

import com.travistrle.core.adapters.user.UserRepository;
import com.travistrle.core.entities.user.User;
import org.apache.commons.lang3.StringUtils;

public class UserManagerImpl implements UserManager {

  private final UserRepository repository;

  public UserManagerImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public boolean validate(User entity) {
    if (entity == null) {
      return false;
    } else if (StringUtils.isBlank(entity.getEmail())) {
      return false;
    }
    return true;
  }

  @Override
  public boolean create(User entity) {
    if (entity == null) {
      return false;
    }
    if (StringUtils.isBlank(entity.getEmail())) {
      return false;
    }
    return repository.create(entity);
  }

  @Override
  public User read(User entity) {
    if (entity == null) {
      return null;
    }
    if (StringUtils.isBlank(entity.getEmail())) {
      return null;
    }
    return repository.read(entity);
  }

  @Override
  public boolean update(User entity) {
    if (entity == null) {
      return false;
    }
    if (StringUtils.isBlank(entity.getEmail())) {
      return false;
    }
    return repository.update(entity);
  }

  @Override
  public boolean delete(User entity) {
    if (entity == null) {
      return false;
    }
    if (StringUtils.isBlank(entity.getEmail())) {
      return false;
    }
    return repository.delete(entity);
  }

  @Override
  public int delete(Iterable<User> entities) {
    return repository.delete(entities);
  }
}
