package edu.cnm.deepdive.budgetmanagerservice.controller;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.Budget;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.User;
import edu.cnm.deepdive.service.BudgetRepository;
import edu.cnm.deepdive.service.TransactionRepository;
import edu.cnm.deepdive.service.UserRepository;
import java.awt.PageAttributes.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

  private final BudgetRepository budgetRepository;
  private final TransactionRepository transactionRepository;
  private final UserRepository userRepository;

  @Autowired
  public BudgetController(BudgetRepository budgetRepository,
      TransactionRepository transactionRepository,
      UserRepository userRepository) {
    this.budgetRepository = budgetRepository;
    this.transactionRepository = transactionRepository;
    this.userRepository = userRepository;
  }
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Budget> get() {return budgetRepository.getAllByOrderByNameAsc()}

  @GetMapping
  consumes = Media



}
