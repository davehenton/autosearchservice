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
  public String toString() {
    final StringBuffer sb = new StringBuffer("Group{");
    sb.append("name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
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
}
