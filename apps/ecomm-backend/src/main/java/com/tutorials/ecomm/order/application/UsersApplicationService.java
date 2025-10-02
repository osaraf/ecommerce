package com.tutorials.ecomm.order.application;

import com.stripe.model.tax.Registration;
import com.tutorials.ecomm.order.domain.user.aggregate.User;
import com.tutorials.ecomm.order.domain.user.repository.UserRepository;
import com.tutorials.ecomm.order.domain.user.service.UserReader;
import com.tutorials.ecomm.order.domain.user.service.UserSynchronizer;
import com.tutorials.ecomm.order.domain.user.vo.UserAddressToUpdate;
import com.tutorials.ecomm.order.domain.user.vo.UserEmail;
import com.tutorials.ecomm.order.infrastructure.secondary.service.kinda.KindeService;
import com.tutorials.ecomm.shared.authentication.application.AuthenticatedUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PublicKey;

@Service
public class UsersApplicationService {

  private final UserSynchronizer userSynchronizer;
  private final UserReader userReader;


  public UsersApplicationService(UserRepository userRepository, KindeService kindeService) {
    this.userSynchronizer = new UserSynchronizer(userRepository,kindeService);
    this.userReader = new UserReader(userRepository);
  }

  @Transactional
  public User getAuthenticatedUserWithSync(Jwt jwtToken, boolean forceResync){
  userSynchronizer.syncWithIdp(jwtToken,forceResync);
  return userReader.getByEmail(new UserEmail(AuthenticatedUser.username().get())).orElseThrow();

  }

  @Transactional(readOnly=true)
  public User getAuthenticatedUser(){
    return userReader.getByEmail(new UserEmail(AuthenticatedUser.username().get())).orElseThrow();
  }


  @Transactional
  public void updateAddress(UserAddressToUpdate userAddressToUpdate){
    userSynchronizer.updateAddress(userAddressToUpdate);
  }
}
