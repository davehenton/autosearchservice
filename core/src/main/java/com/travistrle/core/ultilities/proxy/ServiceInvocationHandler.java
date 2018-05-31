package com.travistrle.core.ultilities.proxy;

import com.google.common.base.Stopwatch;
import com.travistrle.core.ultilities.transaction.CommonAuditKey;
import com.travistrle.core.ultilities.transaction.TransactionManager;
import com.travistrle.core.ultilities.transaction.TransactionStatus;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class ServiceInvocationHandler implements InvocationHandler {

  private Object originalObject;

  public ServiceInvocationHandler(Object originalObject) {
    this.originalObject = originalObject;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) {
    Object result = null;
    final Stopwatch stopwatch = Stopwatch.createStarted();
    try {
      TransactionManager.startTransaction();
      TransactionManager
          .audit(CommonAuditKey.TRANSACTION_ID, TransactionManager.getTransactionId());
      result = method.invoke(originalObject, args);
      TransactionManager
          .audit(CommonAuditKey.TRANSACTION_STATUS, TransactionStatus.SUCCESS.getValue());
    } catch (InvocationTargetException e) {
      TransactionManager
          .audit(CommonAuditKey.TRANSACTION_STATUS, TransactionStatus.FAILURE.getValue());
    } finally {
      TransactionManager.audit(CommonAuditKey.TRANSACTION_EXECUTION_TIME,
          stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
      TransactionManager.endTransaction();
      return result;
    }
  }
}
