package edu.cnm.deepdive.budgetmanagerservice.service;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.User;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.User.Role;
import java.security.AccessControlException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserService {

  private final UserRepository userRepository;

  /**
   *
   * @param userRepository
   */
  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   *
   * @param oauthKey
   * @param displayName
   * @return
   */
  public synchronized User readOrCreateOne(String oauthKey, String displayName) {
    return userRepository.findFirstByOauth2Key(oauthKey)
        .orElseGet(() -> {
          User user = new User();
          user.setOauth2Key(oauthKey);
          user.setUsername(displayName);
          return userRepository.save(user);
        });
  }

  /**
   *
   * @param id
   * @return
   */
  public Optional<User> get(Long id) {
    return userRepository.findById(id);
  }

  public Optional<User> get(Authentication auth) {
    return Optional.ofNullable((User) auth.getPrincipal());
  }

  public void requireAccess(User current, User owner) {
    if (!current.getId().equals(owner.getId()) && current.getRole() != Role.ADMINISTRATOR) {
      throw new AccessControlException("Insufficient access.");
    }
  }
}