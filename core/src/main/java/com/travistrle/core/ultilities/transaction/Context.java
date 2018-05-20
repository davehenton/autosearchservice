package com.travistrle.core.ultilities.transaction;

import com.travistrle.core.ultilities.CommonSymbols;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Context {

  private String transactionId;
  private Map<String, String> audits;

  public Context() {
    transactionId = UUID.randomUUID().toString();
    audits = new HashMap<>();
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
    return new HashMap<>();
  }

  /**
   * Get all audits with format key2=1 key2=2.
   *
   * @return {@link String}
   */
  public String getAudis() {
    StringBuilder sb = new StringBuilder();
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Context)) {
      return false;
    }
    Context context = (Context) o;
    return Objects.equals(getTransactionId(), context.getTransactionId())
        && Objects.equals(getRawAudits(), context.getRawAudits());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getTransactionId());
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Context{");
    sb.append("transactionId='").append(transactionId).append('\'');
    sb.append(", audits=").append(audits);
    sb.append('}');
    return sb.toString();
  }
}
