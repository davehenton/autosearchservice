package com.travistrle.core.ultilities.proxy;

public class MockServiceResponse extends ServiceResponse {

  private int mockValue;

  public int getMockValue() {
    return mockValue;
  }

  public void setMockValue(int mockValue) {
    this.mockValue = mockValue;
  }
}
