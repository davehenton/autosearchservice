package com.travistrle.infrastructure.db.mysql.auto;

import com.travistrle.core.entities.auto.Auto;
import com.travistrle.infrastructure.db.mysql.user.UserEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "auto")
public class AutoEntity {

  @Id
  @Column(name = "vin")
  private String vehicleIdentificationNumber;

  @Column(name = "make")
  private String make;

  @Column(name = "model")
  private String model;

  @Column(name = "trim")
  private String trim;

  @Column(name = "price")
  private Float price;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
  @JoinColumn(name = "email")
  private UserEntity user;

  public AutoEntity() {
  }

  /**
   * Convert to AutoEntity in infrastructure.
   *
   * @param auto {@link AutoEntity}
   */
  public AutoEntity(Auto auto) {
    this.vehicleIdentificationNumber = auto.getVehicleIdentificationNumber();
    this.make = auto.getMake();
    this.model = auto.getModel();
    this.trim = auto.getTrim();
    this.price = auto.getPrice();
  }

  /**
   * convert to Auto entity in core.
   */
  public Auto toAuto() {
    return new Auto.Builder()
        .withVehicleIdentificationNumber(vehicleIdentificationNumber)
        .withMake(make)
        .withModel(model)
        .withTrim(trim)
        .withPrice(price)
        .build();
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AutoEntity)) {
      return false;
    }
    AutoEntity that = (AutoEntity) o;
    return Objects
        .equals(getVehicleIdentificationNumber(), that.getVehicleIdentificationNumber());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getVehicleIdentificationNumber());
  }

  public static final class Builder {

    private AutoEntity autoEntity;

    public static Builder anAutoDataModel() {
      return new Builder();
    }

    public Builder withVehicleIdentificationNumber(String vehicleIdentificationNumber) {
      autoEntity.setVehicleIdentificationNumber(vehicleIdentificationNumber);
      return this;
    }

    public Builder withMake(String make) {
      autoEntity.setMake(make);
      return this;
    }

    public Builder withModel(String model) {
      autoEntity.setModel(model);
      return this;
    }

    public Builder withTrim(String trim) {
      autoEntity.setTrim(trim);
      return this;
    }

    public Builder withPrice(Float price) {
      autoEntity.setPrice(price);
      return this;
    }

    public AutoEntity build() {
      return autoEntity;
    }
  }
}
