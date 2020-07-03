package edu.cnm.deepdive.service;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.Budget;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.Transaction;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Iterable<User> getAllByOrderByTextAsc();

  Iterable<User> getAllByBudgetOrderByTextAsc(Budget budget);

  Iterable<User> getAllByTransactionContainingOrderByTextAsc(Transaction transaction);

}
