package com.travistrle.core.ultilities.transaction;

import java.util.List;
import java.util.stream.Collectors;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContextTest {

  @Test
  public void testGetTransactionId() {
    Context context1 = new Context();
    Context context2 = new Context();

    Assert.assertNotEquals(context1.getTransactionId(), context2.getTransactionId(),
        "Two different contexts must have different transaction Id");
  }

  @Test
  public void testAuditAndGetAudits() {
    Context context = new Context();
    context.audit("a", "value1");
    context.audit("b", "value2");
    context.audit("c", "value3");
    context.audit("aa", "value4");
    context.audit("ab", "value5");
    context.audit("ac", "value6");
    context.audit("bd", 5);
    List<String> keys = context.getRawAudits().keySet().stream().collect(Collectors.toList());
    for (int i = 1; i < keys.size(); i++) {
      Assert.assertTrue(keys.get(i - 1).compareTo(keys.get(i)) < 0);
    }
  }
}