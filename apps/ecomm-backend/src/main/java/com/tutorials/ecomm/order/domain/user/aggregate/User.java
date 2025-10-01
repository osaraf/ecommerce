package com.tutorials.ecomm.order.domain.user.aggregate;

import com.tutorials.ecomm.order.domain.user.vo.*;
import com.tutorials.ecomm.shared.error.domain.Assert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@Getter
@AllArgsConstructor
public class User {

  private Long dbId;
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
    Set<Authority> authorities = rolesFromAccessToken.stream().map(auth -> Authority.AuthorityBuilder.authority().name(new AuthorityName(auth)).build()).collect(Collectors.toSet());
    userBuilder.authorities(authorities);

    return userBuilder.build();

  }


  public static class UserBuilder {
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

    public UserBuilder() {
    }

    public static UserBuilder user() {
      return new UserBuilder();
    }

    public UserBuilder id(final Long id) {
      this.id = id;
      return this;
    }

    public UserBuilder userFirstName(final UserFirstName userFirstName) {
      this.userFirstName = userFirstName;
      return this;
    }

    public UserBuilder userLastName(final UserLastName userLastName) {
      this.userLastName = userLastName;
      return this;
    }

    public UserBuilder userEmail(final UserEmail userEmail) {
      this.userEmail = userEmail;
      return this;
    }

    public UserBuilder userAddress(final UserAddress userAddress) {
      this.userAddress = userAddress;
      return this;
    }

    public UserBuilder userPublicID(final UserPublicID userPublicID) {
      this.userPublicID = userPublicID;
      return this;
    }

    public UserBuilder userImageUrl(final UserImageUrl userImageUrl) {
      this.userImageUrl = userImageUrl;
      return this;
    }

    public UserBuilder createDate(final Instant createDate) {
      this.createDate = createDate;
      return this;
    }

    public UserBuilder lastModifiedDate(final Instant lastModifiedDate) {
      this.lastModifiedDate = lastModifiedDate;
      return this;
    }

    public UserBuilder lastSeen(final Instant lastSeen) {
      this.lastSeen = lastSeen;
      return this;
    }

    public UserBuilder authorities(final Set<Authority> authorities) {
      this.authorities = authorities;
      return this;
    }

    public User build() {
      return new User(this.id, this.userFirstName, this.userLastName, this.userEmail, this.userAddress, this.userPublicID, this.userImageUrl, this.createDate, this.lastModifiedDate, this.lastSeen, this.authorities);
    }
  }

}
