package com.tutorials.ecomm.order.infrastructure.primary;

import com.tutorials.ecomm.order.application.UsersApplicationService;
import com.tutorials.ecomm.order.domain.user.aggregate.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin()
public class UserResource {

  @Autowired
  private  UsersApplicationService usersApplicationService;

  @GetMapping("/authenticated")
  public ResponseEntity<RestUser> getAuthenticatedUser(@AuthenticationPrincipal Jwt jwtToken, @RequestParam boolean forceResync){
    System.out.println("endpoint authenticated get called");
    final User autheticatedUser = usersApplicationService.getAuthenticatedUserWithSync(jwtToken, forceResync);

    final RestUser restUser = RestUser.from(autheticatedUser);
    return ResponseEntity.ok(restUser);
  }

}
