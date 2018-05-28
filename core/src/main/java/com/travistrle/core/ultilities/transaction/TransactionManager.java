package com.travistrle.core.ultilities.transaction;

public final class TransactionManager {

  private TransactionManager() {

  }

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
   * Audit a value to transaction.
   *
   * @param key {@link AuditKey}
   * @param value {@link T}
   */
  public static <T extends Object> void audit(AuditKey key, T value) {
    context.get().audit(key.getAuditKey(), value);
  }

  /**
   * Get all audit keys in context.
   *
   * @return {@link String}
   */
  public static String getAuditValues() {
    return context.get().getAudits();
  }
}
