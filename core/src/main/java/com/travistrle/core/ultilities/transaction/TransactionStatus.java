package com.travistrle.core.ultilities.transaction;

public enum TransactionStatus {
  SUCCESS("Success"),
  FAILURE("Failure");

  private String value;

  TransactionStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
