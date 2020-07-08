package edu.cnm.deepdive.budgetmanagerservice.service;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.Budget;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.Transaction;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

  Iterable<Budget> getAllByOrderByNameAsc();

  Iterable<Budget> getAllByOrderByBudgetedAmountAsc();

  Iterable<Budget> getAllByOrderByStartDate();

  Iterable<Budget> getAllByOrderByEndDate();

  Iterable<Budget> getAllByTransactionOrderByTextAsc(Transaction transaction);


}
