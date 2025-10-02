package com.tutorials.ecomm.order.infrastructure.primary;

import com.tutorials.ecomm.order.domain.user.aggregate.User;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record RestUser( UUID publicId,
                        String firstName,
                        String lastName,
                        String email,
                        String imageUrl,
                        Set<String> authorities) {

  public static RestUser from(User user){

    RestUserBuilder restUserBuilder = RestUser.builder();

    if(user.getUserImageUrl() != null) {
      restUserBuilder.imageUrl(user.getUserImageUrl().value());
    }

    return restUserBuilder
      .email(user.getUserEmail().value())
      .firstName(user.getUserFirstName().value())
      .lastName(user.getUserLastName().value())
      .publicId(user.getUserPublicID().value())
      .authorities(RestAuthority.fromSet(user.getAuthorities()))
      .build();
  }
}
