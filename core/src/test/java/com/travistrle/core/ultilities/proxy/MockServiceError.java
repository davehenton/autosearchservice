package com.travistrle.core.ultilities.proxy;

public enum MockServiceError implements ServiceError {
  MOCK_SERVICE_ERROR(-2, "mock service error");

  int errorCode;
  String errorMessage;

  MockServiceError(int code, String message) {
    this.errorCode = code;
    this.errorMessage = message;
  }

  @Override
  public int getErrorCode() {
    return errorCode;
  }

  @Override
  public String getErrorMessage() {
    return errorMessage;
  }
}
