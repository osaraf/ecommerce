package com.tutorials.ecomm.shared.authentication.domain;


import com.tutorials.ecomm.shared.error.domain.Assert;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public record Username(String username) {
  public Username {
    Assert.field("username", username).notBlank().maxLength(100);
    Assert.notBlank("hi","there");
  }

  public String get() {
    return username();
  }

  public static Optional<Username> of(String username) {
    return Optional.ofNullable(username).filter(StringUtils::isNotBlank).map(Username::new);
  }

}
