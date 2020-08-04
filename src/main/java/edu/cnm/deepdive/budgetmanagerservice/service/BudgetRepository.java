package edu.cnm.deepdive.budgetmanagerservice.service;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.Budget;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.Transaction;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Budget Repository Interface provides any additional queries required by the project’s REST
 * service endpoints for the Budget class.
 */
public interface BudgetRepository extends JpaRepository<Budget, Long> {

  Iterable<Budget> getAllByOrderByNameAsc();

  Iterable<Budget> getAllByOrderByBudgetedAmountAsc();

  Iterable<Budget> getAllByOrderByStartDate();

  Iterable<Budget> getAllByOrderByEndDate();

  Iterable<Budget> getAllByUserOrderByNameAsc(User user);

}
