package com.travistrle.core.usecases.user;

import com.travistrle.core.adapters.user.UserRepository;
import com.travistrle.core.entities.user.User;

public class UserManagerImpl implements UserManager {

  private final UserRepository repository;

  public UserManagerImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public void create(User entity) {

  }

  @Override
  public User read(User entity) {
    return null;
  }

  @Override
  public void update(User entity) {

  }

  @Override
  public void delete(User entity) {

  }

  @Override
  public int delete(Iterable<User> entities) {
    return 0;
  }
}
