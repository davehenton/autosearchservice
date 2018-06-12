package com.travistrle.infrastructure.db.mysql.user;

import com.travistrle.core.entities.user.ContactMethod;
import com.travistrle.core.entities.user.User;
import com.travistrle.infrastructure.db.mysql.auto.AutoEntity;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {

  @Id
  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "phoneNumber")
  private String phoneNumber;

  @Column(name = "dateOfBirth")
  private ZonedDateTime dateOfBirth;

  @Column(name = "contactMethod")
  private String contactMethod;

  @OneToMany(mappedBy = "user", targetEntity = AutoEntity.class, cascade = CascadeType.ALL)
  private List<AutoEntity> autos;

  public UserEntity() {
    this.autos = new ArrayList<>();
  }

  /**
   * Convert core entity to infrastructure entity.
   *
   * @param user {@link User}
   */
  public UserEntity(User user) {
    this.email = user.getEmail();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.phoneNumber = user.getPhoneNumber();
    this.dateOfBirth = user.getDateOfBirth();
    if (user.getContactMethod() != null) {
      this.contactMethod = user.getContactMethod().getValue();
    }
    if (user.getAutos() != null) {
      this.autos = StreamSupport.stream(user.getAutos().spliterator(), false)
          .map(auto -> new AutoEntity(auto))
          .collect(Collectors.toList());
    }
  }

  /**
   * Convert infrastructure entity to core entity.
   *
   * @return {@link User}
   */
  public User toUser() {
    return new User.Builder()
        .withEmail(email)
        .withFirstName(firstName)
        .withLastName(lastName)
        .withPhoneNumber(phoneNumber)
        .withDateOfBirth(dateOfBirth)
        .withContactMethod(ContactMethod.fromValue(contactMethod))
        .withAutos(
            autos == null ? null
                : autos.stream().map(auto -> auto.toAuto()).collect(Collectors.toList()))
        .build();
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

  public String getContactMethod() {
    return contactMethod;
  }

  public void setContactMethod(String contactMethod) {
    this.contactMethod = contactMethod;
  }

  public List<AutoEntity> getAutos() {
    return autos;
  }

  public void setAutos(List<AutoEntity> autos) {
    this.autos = autos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserEntity)) {
      return false;
    }
    UserEntity that = (UserEntity) o;
    return Objects.equals(getEmail(), that.getEmail());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getEmail());
  }

  public static final class Builder {

    private UserEntity userEntity;

    public Builder() {
      userEntity = new UserEntity();
    }

    public Builder withEmail(String email) {
      userEntity.setEmail(email);
      return this;
    }

    public Builder withFirstName(String firstName) {
      userEntity.setFirstName(firstName);
      return this;
    }

    public Builder withLastName(String lastName) {
      userEntity.setLastName(lastName);
      return this;
    }

    public Builder withPhoneNumber(String phoneNumber) {
      userEntity.setPhoneNumber(phoneNumber);
      return this;
    }

    public Builder withDateOfBirth(ZonedDateTime dateOfBirth) {
      userEntity.setDateOfBirth(dateOfBirth);
      return this;
    }

    public Builder withContactMethod(String contactMethod) {
      userEntity.setContactMethod(contactMethod);
      return this;
    }

    public Builder withAutos(List<AutoEntity> autos) {
      userEntity.setAutos(autos);
      return this;
    }

    public UserEntity build() {
      return userEntity;
    }
  }
}
