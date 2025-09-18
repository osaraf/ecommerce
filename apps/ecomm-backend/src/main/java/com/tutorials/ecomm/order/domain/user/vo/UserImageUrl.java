package com.tutorials.ecomm.order.domain.user.vo;

import com.tutorials.ecomm.shared.error.domain.Assert;

public record UserImageUrl(String value) {
  public UserImageUrl {
    Assert.field("value",value).maxLength(1000);
  }
}
