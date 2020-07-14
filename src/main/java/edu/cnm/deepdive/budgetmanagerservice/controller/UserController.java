package edu.cnm.deepdive.budgetmanagerservice.controller;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.User;
import edu.cnm.deepdive.budgetmanagerservice.service.BudgetRepository;
import edu.cnm.deepdive.budgetmanagerservice.service.TransactionRepository;
import edu.cnm.deepdive.budgetmanagerservice.service.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserRepository userRepository;
  private final TransactionRepository transactionRepository;
  private final BudgetRepository budgetRepository;

  @Autowired
  public UserController(UserRepository userRepository,
      TransactionRepository transactionRepository, BudgetRepository budgetRepository) {
    this.budgetRepository = budgetRepository;
    this.transactionRepository = transactionRepository;
    this.userRepository = userRepository;
  }
  @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> get(@PathVariable long id) {
    return ResponseEntity.of(userService.get(id));
  }

  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> get(Authentication auth) {
    User user = (auth != null) ? (User) auth.getPrincipal() : null;
    return ResponseEntity.of(Optional.ofNullable(user));
  }
}