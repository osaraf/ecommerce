package com.tutorials.ecomm.order.domain.user.repository;

import com.tutorials.ecomm.order.domain.user.aggregate.User;
import com.tutorials.ecomm.order.domain.user.vo.UserAddress;
import com.tutorials.ecomm.order.domain.user.vo.UserEmail;
import com.tutorials.ecomm.order.domain.user.vo.UserPublicID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface UserRepository {
  void save(User user);
  Optional<User> get(UserPublicID userPublicID);
  Optional<User> getOneByEmail(UserEmail userEmail);
  void updateAddress(UserPublicID userPublicID, UserAddress userAddress);
}
