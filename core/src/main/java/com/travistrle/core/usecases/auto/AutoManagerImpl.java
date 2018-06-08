package com.travistrle.core.usecases.auto;

import com.travistrle.core.adapters.auto.AutoRepository;
import com.travistrle.core.entities.auto.Auto;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

public class AutoManagerImpl implements AutoManager {

  private final AutoRepository repository;

  public AutoManagerImpl(AutoRepository repository) {
    this.repository = repository;
  }

  @Override
  public boolean validate(Auto entity) {
    if (entity == null) {
      return false;
    } else if (StringUtils.isBlank(entity.getVehicleIdentificationNumber())) {
      return false;
    }
    return true;
  }

  @Override
  public boolean create(Auto entity) {
    if (entity == null) {
      return false;
    }
    if (StringUtils.isBlank(entity.getVehicleIdentificationNumber())) {
      return false;
    }
    return repository.create(entity);
  }

  @Override
  public Optional<Auto> read(Auto entity) {
    if (entity == null) {
      return Optional.empty();
    }
    if (!validate(entity)) {
      return Optional.empty();
    }
    return repository.read(entity);
  }

  @Override
  public boolean update(Auto entity) {
    if (entity == null) {
      return false;
    }
    if (StringUtils.isBlank(entity.getVehicleIdentificationNumber())) {
      return false;
    }
    return repository.update(entity);
  }

  @Override
  public boolean delete(Auto entity) {
    if (entity == null) {
      return false;
    }
    if (StringUtils.isBlank(entity.getVehicleIdentificationNumber())) {
      return false;
    }
    return repository.delete(entity);
  }

  @Override
  public int delete(Iterable<Auto> entities) {
    return repository.delete(entities);
  }
}
