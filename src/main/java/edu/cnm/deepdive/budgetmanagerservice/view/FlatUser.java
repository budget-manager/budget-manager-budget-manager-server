package edu.cnm.deepdive.budgetmanagerservice.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import java.util.Date;

/**
 *An interface that shows the top level entities and not the related entities.
 */
@JsonPropertyOrder(value = {"id", "Oauth2Key", "Username"})
public interface FlatUser {

  Long getId();

  Long getOauth2Key();

  Long getUsername();



}