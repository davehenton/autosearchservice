package com.travistrle.core.ultilities.transaction;

import com.travistrle.core.ultilities.CommonSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TransactionManagerTest {

  private String startMockTransaction(int index) {
    TransactionManager.startTransaction();
    TransactionManager.audit(MockAuditKey.TEST_KEY_1, index);
    TransactionManager.audit(MockAuditKey.TEST_KEY_2, String.format("value2:%d", index));
    String ret = TransactionManager.getAuditValues();
    return ret;
  }

  private Map<String, String> parseAuditValues(String auditValues) {
    String[] tokens = auditValues.split(CommonSymbols.SPACE);
    Map<String, String> map = new HashMap<>();
    for (int i = 0; i < tokens.length; i++) {
      String[] keys = tokens[i].split(CommonSymbols.EQUAL);
      map.put(keys[0], keys[1]);
    }
    return map;
  }

  @Test
  public void testGenerateDifferentTransactions() throws InterruptedException, ExecutionException {
    List<Callable<String>> tasks = new ArrayList<>();
    int size = 4;
    ExecutorService executor = Executors.newFixedThreadPool(size);
    final int[] indexes = {1, 2, 3, 4};
    Arrays.stream(indexes).forEach(index -> tasks.add(() -> startMockTransaction(index)));
    List<Future<String>> results = executor.invokeAll(tasks);
    for (Future<String> result : results) {
      Map<String, String> map = parseAuditValues(result.get());
      Assert.assertEquals(map.size(), 2);
      Assert.assertNotNull(map.get(MockAuditKey.TEST_KEY_1.getAuditKey()),
          "transaction must have key 1");
      Assert.assertNotNull(map.get(MockAuditKey.TEST_KEY_2.getAuditKey()),
          "transaction must have key 2");
      Assert.assertEquals(map.get(MockAuditKey.TEST_KEY_2.getAuditKey()),
          "value2:" + map.get(MockAuditKey.TEST_KEY_1.getAuditKey()),
          "values must not be immutable by different threads");
    }
  }
}