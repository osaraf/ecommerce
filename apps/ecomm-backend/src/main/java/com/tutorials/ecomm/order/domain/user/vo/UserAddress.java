package com.tutorials.ecomm.order.domain.user.vo;

import com.tutorials.ecomm.shared.error.domain.Assert;
import lombok.Builder;

@Builder
public record UserAddress(String street, String city, String zip, String country) {
  public UserAddress {
    Assert.field("street", street).notBlank();
    Assert.field("city", city).notBlank();
    Assert.field("zip", zip).notBlank();
    Assert.field("country", country).notBlank();
  }
}
