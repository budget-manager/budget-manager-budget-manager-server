package edu.cnm.deepdive.budgetmanagerservice.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import java.util.Date;

/**
 *An interface that shows the top level entities and not the related entities.
 */
@JsonPropertyOrder(value = {"id", "created", "updated", "thresholdPercent"})
public interface FlatBudget {

  Long getId();

  String getName();

  Long getBudgetedAmount();

  Date getCreated();

  Date getUpdated();

  Double getThresholdPercent();

  Boolean isRecurring();

  URI getHref();


}
