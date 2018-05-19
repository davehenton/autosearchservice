package com.travistrle.core.entities.group;

import com.travistrle.core.entities.Entity;
import java.util.Objects;

public class Group extends Entity {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

    return Objects.hash(getName());
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

    public Group build() {
      return group;
    }
  }
}
