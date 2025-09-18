package com.tutorials.ecomm.order.domain.user.vo;

import com.tutorials.ecomm.shared.error.domain.Assert;
import org.jilt.Builder;

@Builder
public record UserAddressToUpdate(UserPublicID userPublicID,UserAddress userAddress) {
  public UserAddressToUpdate {
    Assert.notNull("userPublicID",userPublicID);
    Assert.notNull("userAddress",userAddress);
  }
}
