package com.travistrle.core.ultilities.transaction;

public enum MockAuditKey implements AuditKey {
  TEST_TRANSACTION_ID("testTransactionId"),
  TEST_KEY_1("testKey1"),
  TEST_KEY_2("testKey2");

  private final String value;

  MockAuditKey(String value) {
    this.value = value;
  }

  @Override
  public String getAuditKey() {
    return value;
  }
}
