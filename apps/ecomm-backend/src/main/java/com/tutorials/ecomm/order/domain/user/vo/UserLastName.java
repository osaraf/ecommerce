package com.tutorials.ecomm.order.domain.user.vo;

import com.tutorials.ecomm.shared.error.domain.Assert;

public record UserLastName(String value) {
  public UserLastName {
    Assert.field("value",value).maxLength(255);
  }
}
