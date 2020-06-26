package edu.cnm.deepdive.budgetmanagerservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.lang.NonNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id", nullable = false, updatable = false)
  private Long id;

  @NonNull
  @Column(length = 100, nullable = false, unique = true)
  private String oauth2Key;

  @NonNull
  @Column(length = 100, nullable = false, unique = true)
  private String username;

  public Long getId() {
    return id;
  }

  @NonNull
  public String getOauth2Key() {
    return oauth2Key;
  }

  public void setOauth2Key(@NonNull String oauth2Key) {
    this.oauth2Key = oauth2Key;
  }

  @NonNull
  public String getUsername() {
    return username;
  }

  public void setUsername(@NonNull String username) {
    this.username = username;
  }
}
