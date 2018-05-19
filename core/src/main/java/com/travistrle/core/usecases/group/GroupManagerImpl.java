package com.travistrle.core.usecases.group;

import com.travistrle.core.adapters.group.GroupRepository;
import com.travistrle.core.entities.group.Group;

public class GroupManagerImpl implements GroupManager {

  private final GroupRepository repository;

  public GroupManagerImpl(GroupRepository repository) {
    this.repository = repository;
  }

  @Override
  public boolean validate(Group entity) {
    return false;
  }

  @Override
  public boolean create(Group entity) {
    return false;
  }

  @Override
  public Group read(Group entity) {
    return null;
  }

  @Override
  public boolean update(Group entity) {
    return false;
  }

  @Override
  public boolean delete(Group entity) {
    return false;
  }

  @Override
  public int delete(Iterable<Group> entities) {
    return 0;
  }
}
