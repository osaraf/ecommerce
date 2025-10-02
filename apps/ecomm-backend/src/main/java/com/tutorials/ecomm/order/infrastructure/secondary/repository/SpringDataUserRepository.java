package com.tutorials.ecomm.order.infrastructure.secondary.repository;

import com.tutorials.ecomm.order.domain.user.aggregate.User;
import com.tutorials.ecomm.order.domain.user.repository.UserRepository;
import com.tutorials.ecomm.order.domain.user.vo.UserAddressToUpdate;
import com.tutorials.ecomm.order.domain.user.vo.UserEmail;
import com.tutorials.ecomm.order.domain.user.vo.UserPublicID;
import com.tutorials.ecomm.order.infrastructure.secondary.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SpringDataUserRepository implements UserRepository {

  private final JpaUserRepository jpaUserRepository;

  public SpringDataUserRepository(JpaUserRepository jpaUserRepository) {
    this.jpaUserRepository = jpaUserRepository;
  }


  @Override
  public void save(User user) {
    if (user.getDbId() != null) {
      final Optional<UserEntity> userToUpdateOpt = jpaUserRepository.findById(user.getDbId());
      if (userToUpdateOpt.isPresent()) {
        final UserEntity userToUpdate = userToUpdateOpt.get();
        userToUpdate.updateFromUser(user);
        jpaUserRepository.saveAndFlush(userToUpdate);
      }
    } else {
      jpaUserRepository.saveAndFlush(UserEntity.from(user));
    }

  }

  @Override
  public Optional<User> get(UserPublicID userPublicID) {
    return jpaUserRepository.findByPublicId(userPublicID.value()).map(UserEntity::toDomain);
  }

  @Override
  public Optional<User> getOneByEmail(UserEmail userEmail) {
    return jpaUserRepository.findByEmail(userEmail.value()).map(UserEntity::toDomain);
  }

  @Override
  public void updateAddress(UserPublicID userPublicID, UserAddressToUpdate userAddress) {
    jpaUserRepository.updateAddress(
      userPublicID.value(),
      userAddress.userAddress().street(),
      userAddress.userAddress().city(),
      userAddress.userAddress().country(),
      userAddress.userAddress().zip());
  }
}
