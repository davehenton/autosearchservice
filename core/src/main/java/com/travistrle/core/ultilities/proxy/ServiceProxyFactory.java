package com.travistrle.core.ultilities.proxy;

import java.lang.reflect.Proxy;

public final class ServiceProxyFactory {

  private ServiceProxyFactory() {

  }

  /**
   * Create new dynamic proxy instance.
   *
   * @param originalService {@link O}
   * @param targetService {@link T}
   * @param <T> link to Target Service (interface).
   * @param <O> link to Original service (concrete implementation).
   * @return {@link T}
   */
  public static <T extends Service, O extends T> T newInstance(O originalService,
      Class<T> targetService) {
    return (T) Proxy.newProxyInstance(originalService.getClass().getClassLoader(),
        new Class<?>[]{targetService}, new ServiceInvocationHandler(originalService));
  }
}
