package com.tutorials.ecomm.order.domain.user.aggregate;

import com.tutorials.ecomm.order.domain.user.vo.AuthorityName;
import com.tutorials.ecomm.order.infrastructure.secondary.entity.AuthorityEntity;
import com.tutorials.ecomm.shared.error.domain.Assert;
import lombok.Builder;


@Builder
public class Authority {
  private AuthorityName name;

  public Authority(AuthorityName authorityName) {
    Assert.notNull("name",authorityName);
    this.name = authorityName;
  }

  public AuthorityName getAuthorityName()
  {
    return name;
  }

  public static class AuthorityBuilder {
    private AuthorityName name;

    public AuthorityBuilder() {
    }

    public static AuthorityBuilder authority() {
      return new AuthorityBuilder();
    }

    public AuthorityBuilder name(final AuthorityName name) {
      this.name = name;
      return this;
    }

    public Authority build() {
      return new Authority(this.name);
    }
  }

}
