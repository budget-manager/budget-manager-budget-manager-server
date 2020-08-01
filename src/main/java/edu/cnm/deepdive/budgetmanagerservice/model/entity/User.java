package edu.cnm.deepdive.budgetmanagerservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.cnm.deepdive.budgetmanagerservice.view.FlatBudget;
import edu.cnm.deepdive.budgetmanagerservice.view.FlatUser;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


/**
 * An entity class that holds user_id as PK, oauth2_key as UQ1 and username as UQ2.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Component
@JsonIgnoreProperties(
    value = {"id", "Oauth2Key", "Username"},
    allowGetters = true,
    ignoreUnknown = true
)
public class User implements FlatUser {

  private static EntityLinks entityLinks;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id", nullable = false, updatable = false)
  private Long id;

  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "user",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
  )
  @OrderBy("name ASC")
  @JsonSerialize(contentAs = FlatBudget.class)
  private List<Budget> budgets = new LinkedList<>();


  @NonNull
  @Column(length = 100, nullable = false, unique = true)
  private Long oauth2Key;

  @NonNull
  @Column(length = 100, nullable = false, unique = true)
  private String username;


  /**
   * getter for id in the User class
   */
  public Long getId() {
    return id;
  }

  /**
   * getter for oauth2Key in the User class
   */
  @NonNull
  public Long getOauth2Key() {
    return oauth2Key;
  }

  /**
   * setter for oauth2Key in the User class
   */
  public void setOauth2Key(@NonNull Long oauth2Key) {
    this.oauth2Key = oauth2Key;
  }

  /**
   * getter for username in the User class
   */
  @NonNull
  public String getUsername() {
    return username;
  }

  /**
   * setter for username in the User class
   */
  public void setUsername(@NonNull String username) {
    this.username = username;
  }

  @PostConstruct
  private void initHateoas() {
    //noinspection ResultOfMethodCallIgnored
    entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(
      @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") EntityLinks entityLinks) {
    User.entityLinks = entityLinks;
  }

  @Override
  public URI getHref() {
    return (id != null) ? entityLinks.linkForItemResource(User.class, id).toUri() : null;

  }

}
