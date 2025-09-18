package com.tutorials.ecomm.order.domain.user.aggregate;

import com.tutorials.ecomm.order.domain.user.vo.*;
import com.tutorials.ecomm.shared.error.domain.Assert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jilt.Builder;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Getter
public class User {

  private Long id;
  private UserFirstName userFirstName;
  private UserLastName userLastName;
  private UserEmail userEmail;
  private UserAddress userAddress;
  private UserPublicID userPublicID;
  private UserImageUrl userImageUrl;
  private Instant createDate;
  private Instant lastModifiedDate;
  private Instant lastSeen;

  private Set<Authority> authorities;

  private void assertMandatoryFields() {
    Assert.notNull("lastname", userLastName);
    Assert.notNull("firstname", userFirstName);
    Assert.notNull("email", userEmail);
    Assert.notNull("authorities", authorities);
  }

  public void updateFromUser(User user) {
    this.userEmail = user.userEmail;
    this.userImageUrl = user.userImageUrl;
    this.userFirstName = user.userFirstName;
    this.userLastName = user.userLastName;
  }

  public void initFieldForSignup() {
    this.userPublicID = new UserPublicID(UUID.randomUUID());
  }

  public static User fromTokenAttribute(Map<String, Object> attributes, List<String> rolesFromAccessToken) {
    UserBuilder userBuilder = UserBuilder.user();
    if (attributes.containsKey("preferred_email")) {
      userBuilder.userEmail(new UserEmail(attributes.get("preferred_email").toString()));
    }
    if (attributes.containsKey("last_name")) {
      userBuilder.userLastName(new UserLastName(attributes.get("last_name").toString()));
    }
    if (attributes.containsKey("first_name")) {
      userBuilder.userFirstName(new UserFirstName(attributes.get("first_name").toString()));
    }
    if (attributes.containsKey("picture")) {
      userBuilder.userImageUrl(new UserImageUrl(attributes.get("picture").toString()));
    }
    if (attributes.containsKey("last_signed_in")) {
      userBuilder.lastSeen(Instant.parse(attributes.get("last_singed_in").toString()));
    }
    Set<Authority> authorities = rolesFromAccessToken.stream().map(auth -> AuthorityBuilder.authority().authorityName(new AuthorityName(auth)).build()).collect(Collectors.toSet());
    userBuilder.authorities(authorities);

    return userBuilder.build();

  }


}
