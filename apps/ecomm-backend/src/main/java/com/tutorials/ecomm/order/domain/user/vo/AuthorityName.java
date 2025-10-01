package com.tutorials.ecomm.order.domain.user.vo;

import com.tutorials.ecomm.shared.error.domain.Assert;
import org.jilt.Builder;

public record AuthorityName(String name) {
  public AuthorityName {
    Assert.field("name",name).notNull().notBlank();
  }
}
