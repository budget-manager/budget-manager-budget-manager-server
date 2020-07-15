package edu.cnm.deepdive.budgetmanagerservice.service;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.Budget;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Transaction Repository Interface provides any additional queries required by the project’s
 * REST service endpoints for the Transaction class.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  Iterable<Transaction> getAllByOrderByDate();

  Iterable<Transaction> getAllByOrderByAmount();

  Iterable<Transaction> getAllByOrderByNameAsc();

  Iterable<Transaction> getAllByOrderByCreated();

  Iterable<Transaction> getAllByBudgetOrderByDate(Budget budget);


}
