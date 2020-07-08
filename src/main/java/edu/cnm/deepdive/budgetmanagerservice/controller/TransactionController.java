package edu.cnm.deepdive.budgetmanagerservice.controller;


import edu.cnm.deepdive.budgetmanagerservice.model.entity.Budget;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.Transaction;
import edu.cnm.deepdive.budgetmanagerservice.service.BudgetRepository;
import edu.cnm.deepdive.budgetmanagerservice.service.TransactionRepository;
import edu.cnm.deepdive.budgetmanagerservice.service.UserRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {


  private final UserRepository userRepository;
  private final TransactionRepository transactionRepository;
  private final BudgetRepository budgetRepository;

  @Autowired
  public TransactionController
      (UserRepository userRepository,
          TransactionRepository transactionRepository,
          BudgetRepository budgetRepository) {
    this.userRepository = userRepository;
    this.transactionRepository = transactionRepository;
    this.budgetRepository = budgetRepository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Transaction> get() {
    return transactionRepository.getAllByOrderByNameAsc();
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Transaction post(@RequestBody Transaction transaction) {
    return transactionRepository.save(transaction);
  }

  @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Transaction get(@PathVariable long id) {
    return transactionRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }



}




