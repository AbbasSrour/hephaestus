package com.abbassrour.backend.user.controller;

import com.abbassrour.backend.exception.APIException;
import com.abbassrour.backend.user.exception.UserErrorCodes;
import com.abbassrour.backend.user.model.User;
import com.abbassrour.backend.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "The User API")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public User get(@PathVariable("id") UUID id) throws APIException {
    try {
      return userService.get(id);
    } catch (RuntimeException e) {
      if (e.getMessage().equals(UserErrorCodes.NOT_FOUND.getMessage())) {
        throw new APIException(UserErrorCodes.NOT_FOUND);
      }

      throw new APIException(UserErrorCodes.UNKNOWN_ERROR);
    } catch (Exception e) {
      throw new APIException(UserErrorCodes.UNKNOWN_ERROR);
    }
  }
}
