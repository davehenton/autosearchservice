package com.travistrle.core.ultilities.proxy;

public final class ServiceException extends RuntimeException {

  private ServiceError serviceError;

  /**
   * Initialize service exception.
   *
   * @param serviceError {@link ServiceError}
   */
  public ServiceException(ServiceError serviceError) {
    this.serviceError = serviceError;
  }

  /**
   * Get error code.
   *
   * @return {@link Integer}
   */
  public int getErrorCode() {
    return serviceError.getErrorCode();
  }

  /**
   * Get error message.
   *
   * @return {@link String}
   */
  public String getErrorMessage() {
    return serviceError.getErrorMessage();
  }
}
