package com.travistrle.infrastructure.db.mysql.user;

import com.travistrle.core.adapters.user.UserRepository;
import com.travistrle.core.entities.user.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserRepositoryImpl implements UserRepository {

  private final UserRepositoryMySql repository;

  public UserRepositoryImpl(UserRepositoryMySql repository) {
    this.repository = repository;
  }

  @Override
  public boolean create(User entity) {
    repository.save(new UserEntity(entity));
    return true;
  }

  @Override
  public int create(Iterable<User> entities) {
    List<UserEntity> userEntities = StreamSupport.stream(entities.spliterator(), false)
        .map(entity -> new UserEntity(entity))
        .collect(Collectors.toList());

    List<UserEntity> retUserEntities = repository.saveAll(userEntities);
    return retUserEntities.size();
  }

  @Override
  public Optional<User> read(User entity) {
    return repository.findById(entity.getEmail())
        .map(userEntity -> userEntity.toUser());
  }

  @Override
  public List<User> read(Iterable<User> entities) {
    List<String> ids = StreamSupport.stream(entities.spliterator(), false)
        .map(entity -> entity.getEmail())
        .collect(Collectors.toList());
    List<UserEntity> userEntities = repository.findAllById(ids);

    return userEntities.stream().map(entity -> entity.toUser()).collect(Collectors.toList());
  }

  @Override
  public List<User> readAll() {
    return repository.findAll().stream()
        .map(entity -> entity.toUser())
        .collect(Collectors.toList());
  }

  @Override
  public boolean update(User entity) {
    return create(entity);
  }

  @Override
  public int update(Iterable<User> entities) {
    return create(entities);
  }

  @Override
  public boolean delete(User entity) {
    repository.delete(new UserEntity(entity));
    return false;
  }

  @Override
  public int delete(Iterable<User> entities) {
    List<UserEntity> userEntities = StreamSupport.stream(entities.spliterator(), false)
        .map(entity -> new UserEntity(entity))
        .collect(Collectors.toList());
    repository.deleteAll(userEntities);
    return 0;
  }
}
