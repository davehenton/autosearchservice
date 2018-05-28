package com.travistrle.core.ultilities.logging;

import org.testng.annotations.Test;

public class LoggerTest {

  @Test
  public void testLog() {
    Logger.info("Truong Test Message info");
    Logger.error("Truong Test Message error");
    Logger.debug("Truong Test Message debug");
    Logger.warn("Truong Test Message warn");
  }

  @Test
  public void testLogWithFormatMessage() {
    Logger.info("Truong Test Message info {} {}", "test", 1);
    Logger.error("Truong Test Message error {} {}", "test", 1);
    Logger.debug("Truong Test Message debug {} {}", "test", 1);
    Logger.warn("Truong Test Message warn {} {}", "test", 1);
  }
}