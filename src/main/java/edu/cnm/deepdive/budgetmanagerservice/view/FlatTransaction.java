package edu.cnm.deepdive.budgetmanagerservice.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.cnm.deepdive.budgetmanagerservice.model.entity.Budget;
import java.net.URI;
import java.util.Date;


/**
 *An interface that shows the top level entities and not the related entities.
 */
@JsonPropertyOrder(value = {"id", "budget", "date", "amount", "note", "created"})
public interface FlatTransaction {

  Long getId();

  Budget getBudget();

  Date getDate();

  Long getAmount();

  String getNote();

  Date getCreated();

  URI getHref();

}
