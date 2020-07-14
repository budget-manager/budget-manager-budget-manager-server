package edu.cnm.deepdive.budgetmanagerservice.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import java.util.Date;

@JsonPropertyOrder(value = {"id", "created", "Amount", "Name", "Note", "updated", "href"})
public interface FlatTransaction {

  Long getId();

  Date getCreated();

  Long getAmount();

  String getName();

  String getNote();

  Date getUpdated();

  URI getHref();

}
