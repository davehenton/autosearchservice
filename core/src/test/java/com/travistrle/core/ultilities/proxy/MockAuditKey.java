package com.travistrle.core.ultilities.proxy;

import com.travistrle.core.ultilities.transaction.AuditKey;

public enum MockAuditKey implements AuditKey {
  TEST_KEY_A("testKeyA"),
  TEST_KEY_B("testKeyB");

  private final String value;

  MockAuditKey(String value) {
    this.value = value;
  }

  @Override
  public String getAuditKey() {
    return value;
  }
}
