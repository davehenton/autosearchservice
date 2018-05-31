package com.travistrle.core.entities.user;

import com.google.common.collect.Lists;
import com.travistrle.core.entities.Entity;
import com.travistrle.core.entities.group.Group;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends Entity {

  private String email;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private ZonedDateTime dateOfBirth;
  private ContactMethod contactMethod;
  private List<Group> groups;

  public User() {
    this.groups = new ArrayList<>();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public ZonedDateTime getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(ZonedDateTime dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public ContactMethod getContactMethod() {
    return contactMethod;
  }

  public void setContactMethod(ContactMethod contactMethod) {
    this.contactMethod = contactMethod;
  }

  public Iterable<Group> getGroups() {
    return new ArrayList<>(this.groups);
  }

  public void setGroups(Iterable<Group> groups) {
    this.groups = Lists.newArrayList(groups);
  }

  public void addGroup(Group group) {
    this.groups.add(group);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(getEmail(), user.getEmail());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getEmail());
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("User{");
    sb.append("email='").append(email).append('\'');
    sb.append(", firstName='").append(firstName).append('\'');
    sb.append(", lastName='").append(lastName).append('\'');
    sb.append(", phoneNumber='").append(phoneNumber).append('\'');
    sb.append(", dateOfBirth=").append(dateOfBirth);
    sb.append(", contactMethod=").append(contactMethod);
    sb.append('}');
    return sb.toString();
  }


  public static final class Builder {

    private User user;

    public Builder() {
      user = new User();
    }

    public Builder withCreatedDate(Long createdDate) {
      user.setCreatedDate(createdDate);
      return this;
    }

    public Builder withUpdatedDate(Long updatedDate) {
      user.setUpdatedDate(updatedDate);
      return this;
    }

    public Builder withEmail(String email) {
      user.setEmail(email);
      return this;
    }

    public Builder withFirstName(String firstName) {
      user.setFirstName(firstName);
      return this;
    }

    public Builder withLastName(String lastName) {
      user.setLastName(lastName);
      return this;
    }

    public Builder withPhoneNumber(String phoneNumber) {
      user.setPhoneNumber(phoneNumber);
      return this;
    }

    public Builder withDateOfBirth(ZonedDateTime dateOfBirth) {
      user.setDateOfBirth(dateOfBirth);
      return this;
    }

    public Builder withContactMethod(ContactMethod contactMethod) {
      user.setContactMethod(contactMethod);
      return this;
    }

    public Builder withGroups(List<Group> groups) {
      user.setGroups(groups);
      return this;
    }

    public User build() {
      return user;
    }
  }
}
