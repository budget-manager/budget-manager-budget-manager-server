package edu.cnm.deepdive.budgetmanagerservice.service;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * The User Repository Interface provides any additional queries required by the project’s REST
 * service endpoints for the User class.
 */
public interface UserRepository extends JpaRepository<User, Long> {

  Iterable<User> getAllByOrderByUsernameAsc();

  Optional<User> findFirstByOauth2Key(String key);

}
