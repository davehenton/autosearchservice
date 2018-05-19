package com.travistrle.core.ultilities.transaction;

public interface TransactionManager {

  /**
   * start transaction.
   */
  void startTransaction();

  /**
   * end transaction.
   */
  void endTransaction();
}
