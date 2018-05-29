package com.travistrle.core.ultilities.transaction;

import com.travistrle.core.ultilities.CommonSymbols;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class Context {

  private final String transactionId;
  private final Map<String, String> audits;

  /**
   * Initialize Context.
   */
  public Context() {
    transactionId = UUID.randomUUID().toString();
    audits = new TreeMap<>();

  }

  public String getTransactionId() {
    return transactionId;
  }

  /**
   * Get all audit keys.
   *
   * @return {@link Map}
   */
  public Map<String, String> getRawAudits() {
    return new TreeMap<>(audits);
  }

  /**
   * Get all audits with format key1=1 key2=2.
   *
   * @return {@link String}
   */
  public String getAudits() {
    StringBuilder sb = new StringBuilder();
    sb.append(CommonAuditKey.TRANSACTION_ID.getAuditKey())
        .append(CommonSymbols.EQUAL)
        .append(transactionId)
        .append(CommonSymbols.SPACE);
    audits.entrySet().forEach(
        audit -> sb.append(audit.getKey())
            .append(CommonSymbols.EQUAL)
            .append(audit.getValue())
            .append(CommonSymbols.SPACE)
    );
    return sb.toString();
  }

  /**
   * Audit a key with value.
   *
   * @param key {@link String}
   * @param value {@link String}
   */
  public <T extends Object> void audit(String key, T value) {
    audits.putIfAbsent(key, value.toString());
  }
}
