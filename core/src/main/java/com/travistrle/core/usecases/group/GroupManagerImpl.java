package com.travistrle.core.usecases.group;

import com.travistrle.core.adapters.group.GroupRepository;
import com.travistrle.core.entities.group.Group;
import org.apache.commons.lang3.StringUtils;

public class GroupManagerImpl implements GroupManager {

  private final GroupRepository repository;

  public GroupManagerImpl(GroupRepository repository) {
    this.repository = repository;
  }

  @Override
  public boolean validate(Group entity) {
    if (entity == null) {
      return false;
    } else if (StringUtils.isBlank(entity.getName())) {
      return false;
    }
    return true;
  }

  @Override
  public boolean create(Group entity) {
    if (entity == null) {
      return false;
    }
    if (StringUtils.isBlank(entity.getName())) {
      return false;
    }
    return repository.create(entity);
  }

  @Override
  public Group read(Group entity) {
    if (entity == null) {
      return null;
    }
    if (StringUtils.isBlank(entity.getName())) {
      return null;
    }
    return repository.read(entity);
  }

  @Override
  public boolean update(Group entity) {
    if (entity == null) {
      return false;
    }
    if (StringUtils.isBlank(entity.getName())) {
      return false;
    }
    return repository.update(entity);
  }

  @Override
  public boolean delete(Group entity) {
    if (entity == null) {
      return false;
    }
    if (StringUtils.isBlank(entity.getName())) {
      return false;
    }
    return repository.delete(entity);
  }

  @Override
  public int delete(Iterable<Group> entities) {
    return repository.delete(entities);
  }
}
