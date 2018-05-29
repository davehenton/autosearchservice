package com.travistrle.core.ultilities.proxy;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ServiceTest {

  @Test
  public void testProxyService() {
    int result = ServiceProxyFactory
        .newInstance(new MockServiceImpl(), MockService.class)
        .sum(5, 7);
    Assert.assertEquals(result, 12);

    ServiceProxyFactory
        .newInstance(new MockServiceImpl(), MockService.class)
        .report(2, 7);
  }

  @Test
  public void testProxyServiceInvalid() {
    ServiceProxyFactory
        .newInstance(new MockServiceImpl(), MockService.class)
        .sum(0, 7);

    ServiceProxyFactory
        .newInstance(new MockServiceImpl(), MockService.class)
        .sum(1, 7);

    ServiceProxyFactory
        .newInstance(new MockServiceImpl(), MockService.class)
        .sum(2, 7);
  }
}