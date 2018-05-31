package com.travistrle.core.entities.group;

import com.google.common.collect.Lists;
import com.travistrle.core.entities.Entity;
import com.travistrle.core.entities.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group extends Entity {

  private String name;
  private String description;
  private List<User> users;

  public Group() {
    this.users = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Iterable<User> getUsers() {
    return new ArrayList<>(this.users);
  }

  public void setUsers(Iterable<User> users) {
    this.users = Lists.newArrayList(users);
  }

  public void addUser(User user) {
    this.users.add(user);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Group)) {
      return false;
    }
    Group group = (Group) o;
    return Objects.equals(getName(), group.getName());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getName(), getDescription());
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Group{");
    sb.append("name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
  }

  public static final class Builder {

    private Group group;

    public Builder() {
      group = new Group();
    }

    public Builder withCreatedDate(Long createdDate) {
      group.setCreatedDate(createdDate);
      return this;
    }

    public Builder withUpdatedDate(Long updatedDate) {
      group.setUpdatedDate(updatedDate);
      return this;
    }

    public Builder withName(String name) {
      group.setName(name);
      return this;
    }

    public Builder withDescription(String description) {
      group.setDescription(description);
      return this;
    }

    public Builder withUsers(List<User> users) {
      group.setUsers(users);
      return this;
    }

    public Group build() {
      return group;
    }
  }
}
