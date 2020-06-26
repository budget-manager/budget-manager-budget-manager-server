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
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class Budget {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "budget_id", nullable = false, updatable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @NonNull
  @Column(length = 100, nullable = false)
  private String name;

  @NonNull
  @Column(length = 100, nullable = false)
  private Long budgetedAmount;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date startDate;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date endDate;

  @NonNull
  @Column(length = 100, nullable = false)
  private Double thresholdPercent;

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  @NonNull
  public Long getBudgetedAmount() {
    return budgetedAmount;
  }

  public void setBudgetedAmount(@NonNull Long budgetedAmount) {
    this.budgetedAmount = budgetedAmount;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  @NonNull
  public Double getThresholdPercent() {
    return thresholdPercent;
  }

  public void setThresholdPercent(@NonNull Double thresholdPercent) {
    this.thresholdPercent = thresholdPercent;
  }
}
