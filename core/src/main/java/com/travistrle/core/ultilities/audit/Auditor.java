package com.travistrle.core.ultilities.audit;

public interface Auditor {

  /**
   * audit value.
   *
   * @param auditKey {@link AuditKey}
   * @param value {@link String}
   */
  void audit(AuditKey auditKey, String value);

  /**
   * audit composite key.
   *
   * @param auditKey {@link AuditKey}
   * @param compositeKey {@link AuditKey}
   * @param compositeValue {@link String}
   */
  void audit(AuditKey auditKey, AuditKey compositeKey, String compositeValue);
}
