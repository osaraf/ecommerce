package com.tutorials.ecomm.order.infrastructure.secondary.entity;

import com.tutorials.ecomm.order.domain.user.aggregate.User;
import com.tutorials.ecomm.order.domain.user.vo.*;
import com.tutorials.ecomm.shared.jpa.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "ecommerce_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity extends AbstractAuditingEntity<Long>{
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserEntity that)) return false;
    return Objects.equals(public_id, that.public_id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(public_id);
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
  @SequenceGenerator(name = "userSeq", sequenceName = "user_sequence",allocationSize = 1)
  @Column(name = "id")
  private Long id;

  @Column(name = "last_name")
  private String lastName;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "email")
  private String email;
  @Column(name = "image_url")
  private String imageUrl;
  @Column(name = "public_id")
  private UUID public_id;
  @Column(name = "address_street")
  private String addressStreet;
  @Column(name = "address_city")
  private String addressCity;
  @Column(name = "address_zip_code")
  private String addressZipCode;
  @Column(name = "address_country")
  private String addressCountry;
  @Column(name = "last_seen")
  private Instant lastSeen;

  @ManyToMany(cascade = CascadeType.REMOVE)
  @JoinTable(name = "user_authority",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")}
  )
  private Set<AuthorityEntity> authorities = new HashSet<>();

  public void updateFromUser(User user) {
    this.email = user.getUserEmail().value();
    this.lastName = user.getUserLastName().value();
    this.firstName = user.getUserFirstName().value();
    this.imageUrl = user.getUserImageUrl().value();
    this.lastSeen = user.getLastSeen();
  }

  public static UserEntity from(User user) {
    UserEntityBuilder userEntityBuilder = UserEntity.builder();

    if (user.getUserImageUrl() != null) {
      userEntityBuilder.imageUrl(user.getUserImageUrl().value());
    }

    if (user.getUserPublicID() != null) {
      userEntityBuilder.public_id(user.getUserPublicID().value());
    }

    if (user.getUserAddress() != null) {
      userEntityBuilder.addressCity(user.getUserAddress().city());
      userEntityBuilder.addressCountry(user.getUserAddress().country());
      userEntityBuilder.addressZipCode(user.getUserAddress().zip());
      userEntityBuilder.addressStreet(user.getUserAddress().street());
    }

    return userEntityBuilder
      .authorities(AuthorityEntity.from(user.getAuthorities()))
      .email(user.getUserEmail().value())
      .firstName(user.getUserFirstName().value())
      .lastName(user.getUserLastName().value())
      .lastSeen(user.getLastSeen())
      .id(user.getDbId())
      .build();
  }

  public static User toDomain(UserEntity userEntity) {
    User.UserBuilder userBuilder = User.builder();

    if(userEntity.getImageUrl() != null) {
      userBuilder.userImageUrl(new UserImageUrl(userEntity.getImageUrl()));
    }

    if(userEntity.getAddressStreet() != null) {
      userBuilder.userAddress(
        UserAddress.builder()
          .city(userEntity.getAddressCity())
          .country(userEntity.getAddressCountry())
          .zip(userEntity.getAddressZipCode())
          .street(userEntity.getAddressStreet())
          .build());
    }

    return userBuilder
      .userEmail(new UserEmail(userEntity.getEmail()))
      .userLastName(new UserLastName(userEntity.getLastName()))
      .userFirstName(new UserFirstName(userEntity.getFirstName()))
      .authorities(AuthorityEntity.toDomain(userEntity.getAuthorities()))
      .userPublicID(new UserPublicID(userEntity.getPublic_id()))
      .lastModifiedDate(userEntity.getLastModifiedDate())
      .createDate(userEntity.getCreatedDate())
      .dbId(userEntity.getId())
      .build();
  }

  public static Set<UserEntity> from(List<User> users) {
    return users.stream().map(UserEntity::from).collect(Collectors.toSet());
  }

  public static Set<User> toDomain(List<UserEntity> users) {
    return users.stream().map(UserEntity::toDomain).collect(Collectors.toSet());
  }





}
