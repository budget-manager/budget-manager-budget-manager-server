package edu.cnm.deepdive.budgetmanagerservice.controller;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.Budget;
import edu.cnm.deepdive.budgetmanagerservice.service.BudgetRepository;
import edu.cnm.deepdive.service.TransactionRepository;
import edu.cnm.deepdive.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
}
