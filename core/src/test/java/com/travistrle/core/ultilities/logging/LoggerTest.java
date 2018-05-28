package com.travistrle.core.ultilities.logging;

import org.testng.annotations.Test;

public class LoggerTest {

  @Test
  public void testLog() {
    Logger.info("UmsTest Message info");
    Logger.error("UmsTest Message error");
    Logger.debug("UmsTest Message debug");
    Logger.warn("UmsTest Message warn");
  }

  @Test
  public void testLogWithFormatMessage() {
    Logger.info("UmsTest Message info {} {}", "test", 1);
    Logger.error("UmsTest Message error {} {}", "test", 1);
    Logger.debug("UmsTest Message debug {} {}", "test", 1);
    Logger.warn("UmsTest Message warn {} {}", "test", 1);
  }
}