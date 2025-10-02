package com.tutorials.ecomm.order.infrastructure.primary;

import com.tutorials.ecomm.order.application.UsersApplicationService;
import com.tutorials.ecomm.order.domain.user.aggregate.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserResource {

  @Autowired
  private  UsersApplicationService usersApplicationService;

  @GetMapping("authenticated")
  public ResponseEntity<RestUser> getAuthenticatedUser(@AuthenticationPrincipal Jwt jwtToken, @RequestParam boolean forceResync){

    final User autheticatedUser = usersApplicationService.getAuthenticatedUserWithSync(jwtToken, forceResync);

    final RestUser restUser = RestUser.from(autheticatedUser);
    return ResponseEntity.ok(restUser);
  }

}
