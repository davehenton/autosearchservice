package com.travistrle.core.ultilities.proxy;

public interface ServiceError {

  /**
   * Get error code.
   *
   * @return {@link Integer}
   */
  int getErrorCode();

  /**
   * Get error message.
   *
   * @return {@link String}
   */
  String getErrorMessage();
}
