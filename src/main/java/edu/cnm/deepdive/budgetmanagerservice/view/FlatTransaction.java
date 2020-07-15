package edu.cnm.deepdive.budgetmanagerservice.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import java.util.Date;


/**
 *
 */
@JsonPropertyOrder(value = {"id", "budget", "date", "amount", "note", "created"})
public interface FlatTransaction {

  Long getId();

  Date getBudget();

  Long getDate();

  String getAmount();

  Date getNote();

  Date getCreated();



}
