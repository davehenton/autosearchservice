package com.travistrle.core.ultilities.transaction;

public enum CommonAuditKey implements AuditKey {
  TRANSACTION_ID("transactionId"),
  TRANSACTION_STATUS("transactionStatus"),
  TRANSACTION_ERROR_CODE("transactionErrorCode"),
  TRANSACTION_ERROR_MESSAGE("transactionErrorMessage"),
  TRANSACTION_EXECUTION_TIME("transactionExecutionTime");

  private final String value;

  CommonAuditKey(String value) {
    this.value = value;
  }

  @Override
  public String getAuditKey() {
    return value;
  }
}
