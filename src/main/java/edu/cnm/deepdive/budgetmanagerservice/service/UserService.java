package edu.cnm.deepdive.budgetmanagerservice.service;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public synchronized User readOrCreateOne(String oauthKey, String displayName) {
    return userRepository.findFirstByOauth2Key(oauthKey)
        .orElseGet(() -> {
          User user = new User();
          user.setOauth2Key(oauthKey);
          user.setUsername(displayName);
          return userRepository.save(user);
        });
  }

  public Optional<User> get(Long id) {
    return userRepository.findById(id);
  }

}