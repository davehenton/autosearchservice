package com.travistrle.core.ultilities.proxy;

public enum CoreServiceError implements ServiceError {
  INTERNAL_ERROR(-1, "Internal Service Error");

  int code;
  String message;

  CoreServiceError(int code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public int getErrorCode() {
    return code;
  }

  @Override
  public String getErrorMessage() {
    return message;
  }
}
