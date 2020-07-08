package edu.cnm.deepdive.budgetmanagerservice.service;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.Budget;

public interface BudgetRepository {


  Iterable<Budget> getAllByOrderByNameAsc();

  Iterable<Budget> getAllByOrderByAmountAsc();

  Iterable<Budget> getAllByOrderByRecurringNameAsc();

  Iterable<Budget> getAllByOrderByDateAsc();

}
