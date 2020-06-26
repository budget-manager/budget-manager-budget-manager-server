package edu.cnm.deepdive.budgetmanagerservice.model.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "transaction_id", nullable = false, updatable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "budget_id", nullable = false)
  private Budget budget;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date date;

  @NonNull
  @Column(length = 100, nullable = false)
  private Long amount;

  @NonNull
  @Column(length = 100, nullable = false)
  private String name;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  public Long getId() {
    return id;
  }

  public Budget getBudget() {
    return budget;
  }

  public void setBudget(Budget budget) {
    this.budget = budget;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @NonNull
  public Long getAmount() {
    return amount;
  }

  public void setAmount(@NonNull Long amount) {
    this.amount = amount;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  public Date getCreated() {
    return created;
  }

}
