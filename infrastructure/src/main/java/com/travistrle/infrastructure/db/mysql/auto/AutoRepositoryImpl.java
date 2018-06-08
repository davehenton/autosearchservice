package com.travistrle.infrastructure.db.mysql.auto;

import com.travistrle.core.adapters.auto.AutoRepository;
import com.travistrle.core.entities.auto.Auto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AutoRepositoryImpl implements AutoRepository {

  private final AutoRepositoryMySql repository;

  public AutoRepositoryImpl(AutoRepositoryMySql repository) {
    this.repository = repository;
  }

  @Override
  public boolean create(Auto entity) {
    repository.save(new AutoEntity(entity));
    return true;
  }

  @Override
  public int create(Iterable<Auto> entities) {
    List<AutoEntity> autoEntities = StreamSupport.stream(entities.spliterator(), false)
        .map(entity -> new AutoEntity(entity))
        .collect(Collectors.toList());
    List<AutoEntity> retAutoEntities = repository.saveAll(autoEntities);
    return retAutoEntities.size();
  }

  @Override
  public Optional<Auto> read(Auto entity) {
    return repository.findById(entity.getVehicleIdentificationNumber())
        .map(autoEntity -> autoEntity.toAuto());
  }

  @Override
  public List<Auto> read(Iterable<Auto> entities) {
    List<String> ids = StreamSupport.stream(entities.spliterator(), false)
        .map(entity -> entity.getVehicleIdentificationNumber())
        .collect(Collectors.toList());
    List<AutoEntity> autoEntities = repository.findAllById(ids);

    return autoEntities.stream().map(entity -> entity.toAuto()).collect(Collectors.toList());
  }

  @Override
  public List<Auto> readAll() {
    return repository.findAll().stream()
        .map(entity -> entity.toAuto())
        .collect(Collectors.toList());
  }

  @Override
  public boolean update(Auto entity) {
    return create(entity);
  }

  @Override
  public int update(Iterable<Auto> entities) {
    return create(entities);
  }

  @Override
  public boolean delete(Auto entity) {
    repository.delete(new AutoEntity(entity));
    return false;
  }

  @Override
  public int delete(Iterable<Auto> entities) {
    List<AutoEntity> autoEntities = StreamSupport.stream(entities.spliterator(), false)
        .map(entity -> new AutoEntity(entity))
        .collect(Collectors.toList());
    repository.deleteAll(autoEntities);
    return 0;
  }
}
