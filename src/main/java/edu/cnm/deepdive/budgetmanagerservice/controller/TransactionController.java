package edu.cnm.deepdive.budgetmanagerservice.controller;


import edu.cnm.deepdive.budgetmanagerservice.model.entity.Budget;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.Transaction;
import edu.cnm.deepdive.budgetmanagerservice.service.BudgetRepository;
import edu.cnm.deepdive.budgetmanagerservice.service.TransactionRepository;
import edu.cnm.deepdive.budgetmanagerservice.service.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The TransactionController class controls the data flow for the Transaction class into model
 * object and updates the view whenever data changes.
 */
@RestController
@RequestMapping("/budgets/{budgetId:\\d+}/transactions")
@ExposesResourceFor(Transaction.class)
public class TransactionController {


  private final UserRepository userRepository;
  private final TransactionRepository transactionRepository;
  private final BudgetRepository budgetRepository;

  /**
   * @param userRepository
   * @param transactionRepository
   * @param budgetRepository
   */
  @Autowired
  public TransactionController
  (UserRepository userRepository,
      TransactionRepository transactionRepository,
      BudgetRepository budgetRepository) {
    this.userRepository = userRepository;
    this.transactionRepository = transactionRepository;
    this.budgetRepository = budgetRepository;
  }

  private Budget getBudget(long id) {
    return budgetRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  // TODO Include an authorization parameter and verify that the current user is the owner of the
  //  budget.

  /**
   * @return
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Transaction> get(@PathVariable long budgetId) {
    return budgetRepository.findById(budgetId)
        .map(transactionRepository::getAllByBudgetOrderByDate)
        .orElseThrow(NoSuchElementException::new);
  }

  /**
   * @param id
   * @return
   */
  @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Transaction get(@PathVariable long budgetId, @PathVariable long id) {
    return budgetRepository.findById(budgetId)
        .flatMap((b) -> transactionRepository.findById(id))
        .orElseThrow(NoSuchElementException::new);
  }


  /**
   * @param transaction
   * @return
   */
  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Transaction> post(@PathVariable long budgetId, @RequestBody Transaction transaction) {
    return budgetRepository.findById(budgetId)
        .map((b) -> {
          transaction.setBudget(b);
          transactionRepository.save(transaction);
          return ResponseEntity.created(transaction.getHref()).body(transaction);
        })
        .orElseThrow(NoSuchElementException::new);
  }


  @PutMapping(value = "/{id:\\d+}",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Transaction put(@PathVariable long budgetId, @PathVariable long id, @RequestBody Transaction transaction) {
    return budgetRepository.findById(budgetId)
        .flatMap((b) -> transactionRepository.findById(id))
        .map((existing) -> {
          if (transaction.getName() != null) {
            existing.setName(transaction.getName());
          }
          return transactionRepository.save(existing);
        })
        .orElseThrow(NoSuchElementException::new);
  }



  @DeleteMapping(value = "/{id:\\d+}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable long budgetId, @PathVariable long id) {
    budgetRepository.findById(budgetId)
        .flatMap((b) -> transactionRepository.findById(id))
        .map((t) -> {
          transactionRepository.delete(t);
          return null;
        })
        .orElse(null);
  }


}




