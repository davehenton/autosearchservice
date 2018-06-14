package com.travistrle.core.ultilities.proxy;

import com.google.common.base.Stopwatch;
import com.travistrle.core.ultilities.logging.Logger;
import com.travistrle.core.ultilities.transaction.CommonAuditKey;
import com.travistrle.core.ultilities.transaction.TransactionManager;
import com.travistrle.core.ultilities.transaction.TransactionStatus;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class ServiceInvocationHandler implements InvocationHandler {

  private Object originalObject;
  private Stopwatch stopwatch;

  public ServiceInvocationHandler(Object originalObject) {
    this.originalObject = originalObject;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) {
    Object result = null;
    try {
      stopwatch = Stopwatch.createStarted();
      TransactionManager.startTransaction();
      TransactionManager
          .audit(CommonAuditKey.TRANSACTION_ID, TransactionManager.getTransactionId());
      result = method.invoke(originalObject, args);
      TransactionManager
          .audit(CommonAuditKey.TRANSACTION_STATUS, TransactionStatus.SUCCESS.getValue());
    } catch (InvocationTargetException e) {
      result = saveServiceErrorIntoResponse(method, result, e);
      TransactionManager
          .audit(CommonAuditKey.TRANSACTION_STATUS, TransactionStatus.FAILURE.getValue());
    } finally {
      TransactionManager.audit(CommonAuditKey.TRANSACTION_EXECUTION_TIME,
          stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
      TransactionManager.endTransaction();
      return result;
    }
  }

  private Object saveServiceErrorIntoResponse(Method method, Object result,
      InvocationTargetException e) throws InstantiationException, IllegalAccessException {
    Class returnType = method.getReturnType();
    if (returnType.getSuperclass().equals(ServiceResponse.class)) {
      if (e.getCause() instanceof ServiceException) {
        ServiceResponse response = (ServiceResponse) returnType.newInstance();
        ServiceException serviceException = (ServiceException) e.getCause();
        response.setErrorCode(serviceException.getErrorCode());
        response.setErrorMessage(serviceException.getErrorMessage());
        TransactionManager
            .audit(CommonAuditKey.TRANSACTION_ERROR_CODE, serviceException.getErrorCode());
        result = response;
      } else {
        ServiceResponse response = (ServiceResponse) returnType.newInstance();
        response.setErrorCode(CoreServiceError.INTERNAL_ERROR.getErrorCode());
        response.setErrorMessage(CoreServiceError.INTERNAL_ERROR.getErrorMessage());
        TransactionManager.audit(CommonAuditKey.TRANSACTION_ERROR_CODE,
            CoreServiceError.INTERNAL_ERROR.getErrorCode());
        result = response;
      }
    }
    return result;
  }
}
