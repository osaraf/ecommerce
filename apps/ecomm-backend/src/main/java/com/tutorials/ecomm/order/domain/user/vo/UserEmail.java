package com.tutorials.ecomm.order.domain.user.vo;

import com.tutorials.ecomm.shared.error.domain.Assert;

import java.util.regex.Pattern;

public record UserEmail(String value) {

  private static final Pattern EMAIL_PATTERN =
    Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

  public UserEmail {
    Assert.field("value",value).notNull().notBlank().maxLength(255);
    if (!EMAIL_PATTERN.matcher(value).matches()) {
      throw new IllegalArgumentException("Invalid email format: " + value);
    }


  }
}
