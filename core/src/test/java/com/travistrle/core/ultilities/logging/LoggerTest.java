package com.travistrle.core.ultilities.logging;

import org.testng.annotations.Test;

public class LoggerTest {

  @Test
  public void testInfo() {
    Logger.info("Truong Test Message info");
    Logger.error("Truong Test Message error");
    Logger.debug("Truong Test Message debug");
    Logger.warn("Truong Test Message warn");
  }

  @Test
  public void testInfo1() {
  }

  @Test
  public void testWarn() {
  }

  @Test
  public void testWarn1() {
  }

  @Test
  public void testDebug() {
  }

  @Test
  public void testDebug1() {
  }

  @Test
  public void testError() {
  }

  @Test
  public void testError1() {
  }
}