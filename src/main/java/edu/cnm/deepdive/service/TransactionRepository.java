package edu.cnm.deepdive.service;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.Budget;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.Transaction;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  Iterable<Transaction> getAllByOrderByTextAsc();

  Iterable<Transaction> getAllByBudgetOrderByTextAsc(Budget budget);

  Iterable<Transaction> getAllByUserContainingOrderByTextAsc(User user);

}
