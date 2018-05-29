package com.travistrle.core.ultilities.proxy;

import com.travistrle.core.ultilities.logging.Logger;
import com.travistrle.core.ultilities.transaction.CommonAuditKey;
import com.travistrle.core.ultilities.transaction.TransactionManager;
import com.travistrle.core.ultilities.transaction.TransactionStatus;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServiceInvocationHandler implements InvocationHandler {

  private Object originalObject;

  public ServiceInvocationHandler(Object originalObject) {
    this.originalObject = originalObject;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Object result = null;

    try {
      TransactionManager.startTransaction();
      result = method.invoke(originalObject, args);
      TransactionManager
          .audit(CommonAuditKey.TRANSACTION_STATUS, TransactionStatus.SUCCESS.getValue());
    } catch (InvocationTargetException e) {
      TransactionManager
          .audit(CommonAuditKey.TRANSACTION_STATUS, TransactionStatus.FAILURE.getValue());
      throw e;
    } catch (Exception e) {
      TransactionManager
          .audit(CommonAuditKey.TRANSACTION_STATUS, TransactionStatus.FAILURE.getValue());
      throw e;
    } finally {
      Logger.info(TransactionManager.getAuditValues());
      TransactionManager.endTransaction();
      return result;
    }
  }
}
