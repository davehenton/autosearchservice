package com.travistrle.core.ultilities.transaction;

public class TransactionManager {

  private static final ThreadLocal<Context> context;

  static {
    context = new ThreadLocal<>();
  }

  /**
   * start a new transaction.
   */
  public static void startTransaction() {
    context.set(new Context());
  }

  /**
   * Get current transaction id.
   */
  public static String getTransactionId() {
    return context.get().getTransactionId();
  }

  /**
   * end transaction.
   */
  public static void endTransaction() {
    context.remove();
  }

  /**
   * audit value.
   *
   * @param key {@link String}
   * @param value {@link T}
   */
  public <T extends Object> void audit(String key, T value) {
    context.get().audit(key, value);
  }
}
