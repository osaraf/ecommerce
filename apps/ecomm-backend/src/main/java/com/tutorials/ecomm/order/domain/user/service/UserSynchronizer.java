package com.tutorials.ecomm.order.domain.user.service;

import com.tutorials.ecomm.order.domain.user.aggregate.User;
import com.tutorials.ecomm.order.domain.user.repository.UserRepository;
import com.tutorials.ecomm.order.domain.user.vo.UserAddressToUpdate;
import com.tutorials.ecomm.order.infrastructure.secondary.service.kinda.KindeService;
import com.tutorials.ecomm.shared.authentication.application.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class UserSynchronizer {

  private final UserRepository userRepository;
  private final KindeService kindeService;

  private static final String UPDATE_AT_KEY = "last_signed_at";



  public void syncWithIdp(Jwt jwtToken, boolean forceResync) {
    Map<String, Object> claims = jwtToken.getClaims();
    List<String> rolesFromToken = AuthenticatedUser.extractRolesFromToken(jwtToken);
    Map<String, Object> userInfo = kindeService.getUserInfo(claims.get("sub").toString());
    User user = User.fromTokenAttribute(userInfo, rolesFromToken);
    Optional<User> existingUser = userRepository.getOneByEmail(user.getUserEmail());
    if (existingUser.isPresent()) {
      if (claims.get(UPDATE_AT_KEY) != null) {
        Instant idpModifiedDate = Instant.ofEpochSecond((Integer) claims.get(UPDATE_AT_KEY));
        Instant lastModifiedDate = existingUser.orElseThrow().getLastModifiedDate();
        if (idpModifiedDate.isAfter(lastModifiedDate) || forceResync) {
          updateUser(user, existingUser.get());
        }
      }

    } else {
      user.initFieldForSignup();
      userRepository.save(user);
    }
  }

  private void updateUser(User user, User existingUser) {
    existingUser.updateFromUser(user);
    userRepository.save(existingUser);
  }

  public void updateAddress(UserAddressToUpdate userAddressToUpdate) {
    userRepository.updateAddress(userAddressToUpdate.userPublicID(), userAddressToUpdate.userAddress());
  }
}
