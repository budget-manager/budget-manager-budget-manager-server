package edu.cnm.deepdive.budgetmanagerservice.service;

import edu.cnm.deepdive.budgetmanagerservice.model.entity.Budget;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.Transaction;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  Iterable<Transaction> getAllByOrderByDate();

  Iterable<Transaction> getAllByOrderByAmount();

  Iterable<Transaction> getAllByOrderByNameAsc();

  Iterable<Transaction> getAllByOrderByCreated();


}
