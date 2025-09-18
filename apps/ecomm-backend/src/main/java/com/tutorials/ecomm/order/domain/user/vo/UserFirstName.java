package com.tutorials.ecomm.order.domain.user.vo;

import com.tutorials.ecomm.shared.error.domain.Assert;

public record UserFirstName(String value) {
  public UserFirstName {
    Assert.field("value",value).maxLength(255);
  }
}
