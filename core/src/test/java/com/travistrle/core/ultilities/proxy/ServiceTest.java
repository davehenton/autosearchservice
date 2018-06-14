package com.travistrle.core.ultilities.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class ServiceTest {

  @Test
  public void testProxyService() {
    int result = ServiceProxyFactory
        .newInstance(new MockServiceImpl(), MockService.class)
        .sum(5, 7);
    assertThat(result).isEqualTo(12);
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

  @Test
  public void testProxyServiceException() {
    MockServiceResponse response = ServiceProxyFactory
        .newInstance(new MockServiceImpl(), MockService.class)
        .mockFunction(0);
    assertThat(response).isNotNull();
    assertThat(response.getErrorCode()).isEqualTo(MockServiceError.MOCK_SERVICE_ERROR.errorCode);
    assertThat(response.getErrorMessage())
        .isEqualTo(MockServiceError.MOCK_SERVICE_ERROR.getErrorMessage());

    response = ServiceProxyFactory
        .newInstance(new MockServiceImpl(), MockService.class)
        .mockFunction(1);
    assertThat(response).isNotNull();
    assertThat(response.getErrorCode()).isEqualTo(CoreServiceError.INTERNAL_ERROR.getErrorCode());
    assertThat(response.getErrorMessage())
        .isEqualTo(CoreServiceError.INTERNAL_ERROR.getErrorMessage());

    response = ServiceProxyFactory
        .newInstance(new MockServiceImpl(), MockService.class)
        .mockFunction(-1);
    assertThat(response).isNotNull();
    assertThat(response.getErrorCode()).isNull();
    assertThat(response.getErrorMessage()).isNull();
    assertThat(response.getMockValue()).isEqualTo(8888);
  }
}