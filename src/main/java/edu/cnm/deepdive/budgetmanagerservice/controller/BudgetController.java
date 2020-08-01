package edu.cnm.deepdive.budgetmanagerservice.controller;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.Budget;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.Transaction;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.User;
import edu.cnm.deepdive.budgetmanagerservice.service.BudgetRepository;
import edu.cnm.deepdive.budgetmanagerservice.service.TransactionRepository;
import edu.cnm.deepdive.budgetmanagerservice.service.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
 * The Budget Controller class controls the data flow for the Budget class into model object and
 * updates the view whenever data changes.
 */
@RestController
@RequestMapping("/budgets")
@ExposesResourceFor(Budget.class)
public class BudgetController {

  private final BudgetRepository budgetRepository;
  private final TransactionRepository transactionRepository;
  private final UserRepository userRepository;


  /**
   * @param budgetRepository
   * @param transactionRepository
   * @param userRepository
   */
  @Autowired
  public BudgetController(BudgetRepository budgetRepository,
      TransactionRepository transactionRepository,
      UserRepository userRepository) {
    this.budgetRepository = budgetRepository;
    this.transactionRepository = transactionRepository;
    this.userRepository = userRepository;
  }

  /**
   * @return
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Budget> get() {
    return budgetRepository.getAllByOrderByNameAsc();
  }


  @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Budget get(@PathVariable long id) {
    return budgetRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }



  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Budget> post(@RequestBody Budget budget, Authentication auth) {
    if (budget.getUser() != null && budget.getUser().getId() != null) {
      budget.setUser(
          userRepository.findById(
              budget.getUser().getId()
          ).orElseThrow(NoSuchElementException::new)
      );
    }

    return ResponseEntity.created(budget.getHref()).body(budget);
  }

  @PutMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Budget put(@PathVariable long id, @RequestBody Budget budget) {
    Budget existing = get(id);
    if (budget.getName() != null) {
      existing.setName(budget.getName());
    }
    if(budget.getStartDate() != null) {
      existing.setStartDate(budget.getStartDate());
    }
    if (budget.getEndDate() != null) {
      existing.setEndDate(budget.getEndDate());
    }
    if (budget.getBudgetedAmount() != null) {
      existing.setBudgetedAmount(budget.getBudgetedAmount());
    }
    return budgetRepository.save(existing);
  }

  @DeleteMapping(value = "/{id:\\d+}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
    budgetRepository.findById(id)
        .map((b) -> {
          budgetRepository.delete(b);
          return null;
        })
        .orElse(null);
  }



}
