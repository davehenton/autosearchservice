package com.travistrle.core.usecases.user;

import com.travistrle.core.adapters.user.UserRepository;
import com.travistrle.core.entities.user.User;

public class UserManagerImpl implements UserManager {

  private final UserRepository repository;

  public UserManagerImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public boolean validate(User entity) {
    return false;
  }

  @Override
  public boolean create(User entity) {
    return false;
  }

  @Override
  public User read(User entity) {
    return null;
  }

  @Override
  public boolean update(User entity) {
    return false;
  }

  @Override
  public boolean delete(User entity) {
    return false;
  }

  @Override
  public int delete(Iterable<User> entities) {
    return 0;
  }
}
