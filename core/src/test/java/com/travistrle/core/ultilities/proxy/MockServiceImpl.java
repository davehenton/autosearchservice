package com.travistrle.core.ultilities.proxy;

import com.travistrle.core.ultilities.logging.Logger;
import com.travistrle.core.ultilities.transaction.TransactionManager;

public class MockServiceImpl implements MockService {

  @Override
  public Integer sum(int a, int b) {
    if (a == 0) {
      throw new IllegalArgumentException();
    }
    TransactionManager.audit(MockAuditKey.TEST_KEY_A, a);
    if (a == 1) {
      throw new NullPointerException();
    }
    TransactionManager.audit(MockAuditKey.TEST_KEY_B, b);
    if (a == 2) {
      throw new IllegalArgumentException();
    }
    return a + b;
  }

  @Override
  public void report(int a, int b) {
    Logger.info("report {} {}", a, b);
  }

  @Override
  public MockServiceResponse mockFunction(int a) {
    if (a == 0) {
      throw new ServiceException(MockServiceError.MOCK_SERVICE_ERROR);
    } else if (a > 0) {
      throw new IllegalArgumentException("test runtime exception");
    } else {
      MockServiceResponse mockServiceResponse = new MockServiceResponse();
      mockServiceResponse.setMockValue(8888);
      return mockServiceResponse;
    }
  }
}
