package com.tutorials.ecomm.order.infrastructure.primary;

import com.tutorials.ecomm.order.domain.user.aggregate.Authority;
import lombok.Builder;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record RestAuthority(String name) {
  public static Set<String> fromSet(Set<Authority> authorities){
    return authorities.stream().map(auth->auth.getAuthorityName().name()).collect(Collectors.toSet());
  }
}
