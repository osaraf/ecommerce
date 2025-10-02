package com.tutorials.ecomm.order.domain.user.service;

import com.tutorials.ecomm.order.domain.user.aggregate.User;
import com.tutorials.ecomm.order.domain.user.repository.UserRepository;
import com.tutorials.ecomm.order.domain.user.vo.UserEmail;
import com.tutorials.ecomm.order.domain.user.vo.UserPublicID;
import org.jilt.Opt;

import java.util.Optional;

public class UserReader {

  private final UserRepository userRepository;

  public UserReader(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> getByEmail(UserEmail userEmail){
    return userRepository.getOneByEmail(userEmail);
  }
  public Optional<User> getByPublicId(UserPublicID userPublicID){
    return userRepository.get(userPublicID);
  }
}
