package com.abbassrour.backend.user.service;

import com.abbassrour.backend.user.exception.UserErrorCodes;
import com.abbassrour.backend.user.model.User;
import com.abbassrour.backend.user.repository.IUserRepo;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final IUserRepo userRepo;

  @Autowired
  public UserService(IUserRepo userRepo) {
    this.userRepo = userRepo;
  }

  public User get(UUID id) {
    User user =  userRepo.findUserById(id);

    if (user == null) {
      throw new RuntimeException(UserErrorCodes.NOT_FOUND.code);
    }

    return user;
  }

}
