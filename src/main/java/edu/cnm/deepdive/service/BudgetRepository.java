package edu.cnm.deepdive.service;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.Budget;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.Transaction;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

  Iterable<Budget> getAllByOrderByTextAsc();

  Iterable<Budget> getAllByTransactionOrderByTextAsc(Transaction transaction);

  Iterable<Budget> getAllByUserContainingOrderByTextAsc(User user);

}
