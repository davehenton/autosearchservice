package com.travistrle.core.entities.auto;

import com.travistrle.core.entities.Entity;
import com.travistrle.core.entities.user.User;
import java.util.Objects;

public class Auto extends Entity {

  private String vehicleIdentificationNumber;
  private String make;
  private String model;
  private String trim;
  private Float price;
  private User user;

  public String getVehicleIdentificationNumber() {
    return vehicleIdentificationNumber;
  }

  public void setVehicleIdentificationNumber(String vehicleIdentificationNumber) {
    this.vehicleIdentificationNumber = vehicleIdentificationNumber;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getTrim() {
    return trim;
  }

  public void setTrim(String trim) {
    this.trim = trim;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Auto)) {
      return false;
    }
    Auto auto = (Auto) o;
    return Objects
        .equals(getVehicleIdentificationNumber(), auto.getVehicleIdentificationNumber());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getVehicleIdentificationNumber());
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Auto{");
    sb.append("vehicleIdentificationNumber='").append(vehicleIdentificationNumber).append('\'');
    sb.append(", make='").append(make).append('\'');
    sb.append(", model='").append(model).append('\'');
    sb.append(", trim='").append(trim).append('\'');
    sb.append(", price=").append(price);
    sb.append(", user=").append(user);
    sb.append('}');
    return sb.toString();
  }

  public static final class Builder {

    private Auto auto;

    public Builder() {
      auto = new Auto();
    }

    public Builder withCreatedDate(Long createdDate) {
      auto.setCreatedDate(createdDate);
      return this;
    }

    public Builder withUpdatedDate(Long updatedDate) {
      auto.setUpdatedDate(updatedDate);
      return this;
    }

    public Builder withVehicleIdentificationNumber(String vehicleIdentificationNumber) {
      auto.setVehicleIdentificationNumber(vehicleIdentificationNumber);
      return this;
    }

    public Builder withMake(String make) {
      auto.setMake(make);
      return this;
    }

    public Builder withModel(String model) {
      auto.setModel(model);
      return this;
    }

    public Builder withTrim(String trim) {
      auto.setTrim(trim);
      return this;
    }

    public Builder withPrice(Float price) {
      auto.setPrice(price);
      return this;
    }

    public Builder withUser(User user) {
      auto.setUser(user);
      return this;
    }

    public Auto build() {
      return auto;
    }
  }
}
