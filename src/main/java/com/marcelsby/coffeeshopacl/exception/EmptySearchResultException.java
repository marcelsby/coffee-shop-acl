package com.marcelsby.coffeeshopacl.exception;

public class EmptySearchResultException extends RuntimeException {

  public EmptySearchResultException() {
    super("Exception.emptySearchResult");
  }
}
