package com.marcelsby.coffeeshopacl.exception;

import com.marcelsby.coffeeshopacl.domain.model.BaseEntity;
import lombok.Getter;

@Getter
public class RecordNotFoundException extends RuntimeException {

  private final transient Object[] args;

  public RecordNotFoundException(Class<? extends BaseEntity> entity) {
    super("Exception.recordNotFound");
    String entityDisplayName = String.join(" ", entity.getSimpleName().split("(?=[A-Z])")).strip().toLowerCase();
    this.args = new Object[]{entityDisplayName};
  }
}
