package edu.cnm.deepdive.budgetmanagerservice.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import java.util.Date;

@JsonPropertyOrder(value = {"id", "created", "updated", "TresholdPercent", "href"})
public interface FlatBudget {

  Long getId();

  String getName();

  Long getAmount();

  Date getCreated();

  Date getUpdated();

  Double getThresholdPercent();

  Boolean getIsRecurring();

  URI getHref();

}
