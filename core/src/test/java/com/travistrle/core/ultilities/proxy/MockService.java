package com.travistrle.core.ultilities.proxy;

public interface MockService extends Service {

  /**
   * process input.
   *
   * @param a {@link Integer}
   * @param b {@link Integer}
   * @return {@link Integer}
   */
  Integer sum(int a, int b);

  /**
   * print out report.
   *
   * @param a {@link Integer}
   * @param b {@link Integer}
   */
  void report(int a, int b);

  /**
   * Throw ServiceException if a = 0, throw RuntimeException if a > 0.
   *
   * @param a {@link Integer}
   * @return {@link MockServiceResponse}
   */
  MockServiceResponse mockFunction(int a);
}
