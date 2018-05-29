package com.travistrle.core.ultilities.logging;

import org.apache.logging.log4j.LogManager;

public final class Logger {

  private static final String UMS_LOG_INSTANCE = "UMS";
  private static final org.apache.logging.log4j.Logger logger = LogManager
      .getLogger(UMS_LOG_INSTANCE);

  private Logger() {

  }

  /**
   * Log at info level.
   *
   * @param message {@link String}
   */
  public static void info(String message) {
    logger.info(message);
  }

  /**
   * Log at info level.
   *
   * @param message {@link String}
   * @param params {@link Object}
   */
  public static void info(String message, Object... params) {
    logger.info(message, params);
  }

  /**
   * Log at warn level.
   *
   * @param message {@link String}
   */
  public static void warn(String message) {
    logger.warn(message);
  }

  /**
   * Log at warn level.
   *
   * @param message {@link String}
   * @param params {@link Object}
   */
  public static void warn(String message, Object... params) {
    logger.warn(message, params);
  }

  /**
   * Log at debug level.
   *
   * @param message {@link String}
   */
  public static void debug(String message) {
    logger.debug(message);
  }

  /**
   * Log at debug level.
   *
   * @param message {@link String}
   * @param params {@link Object}
   */
  public static void debug(String message, Object... params) {
    logger.debug(message, params);
  }

  /**
   * Log at error level.
   *
   * @param message {@link String}
   */
  public static void error(String message) {
    logger.error(message);
  }

  /**
   * Log at error level.
   *
   * @param message {@link String}
   * @param params {@link Object}
   */
  public static void error(String message, Object... params) {
    logger.error(message, params);
  }
}
