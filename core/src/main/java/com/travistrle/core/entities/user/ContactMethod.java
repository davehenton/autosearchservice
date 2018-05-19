package com.travistrle.core.entities.user;

import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.Map;

public enum ContactMethod {
  PHONE("phone"),
  EMAIL("email");
  private String value;


  private static final Map<String, ContactMethod> LOOKUP = Maps.uniqueIndex(
      Arrays.asList(ContactMethod.values()),
      ContactMethod::getValue
  );

  ContactMethod(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public static ContactMethod fromValue(String value) {
    return LOOKUP.get(value);
  }
}
