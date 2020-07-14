package edu.cnm.deepdive.budgetmanagerservice.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import java.util.Date;

@JsonPropertyOrder(value = {"id", "UserName", "href"})
public interface FlatUser {

  Long getId();

//  Long getOauth2Key();

  String getUserName();

  URI getHref();

}