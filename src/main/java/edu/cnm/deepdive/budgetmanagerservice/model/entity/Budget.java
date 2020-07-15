package edu.cnm.deepdive.budgetmanagerservice.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.cnm.deepdive.budgetmanagerservice.view.FlatTransaction;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.lang.NonNull;


/**
 * An entity class that holds budget_id as PK, user_id as FK, name, budget_amount, start_date, end_date,
 * treshold_percent and recurring.
 */
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

  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "budget",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
  )
  @OrderBy("name ASC")
  @JsonSerialize(contentAs = FlatTransaction.class)
  private List<Transaction> transactions = new LinkedList<>();


  @NonNull
  @Column(length = 100, nullable = false)
  private String name;

  @Column(length = 100, nullable = false)
  private long budgetedAmount;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date startDate;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date endDate;

  @Column(length = 100, nullable = false)
  private double thresholdPercent;

  @Column(length = 100)
  private boolean recurring;

  /**
   * getter for id in the Budget class
   */
  public Long getId() {
    return id;
  }

  /**
   * getter for user in the Budget class
   */
  public User getUser() {
    return user;
  }

  /**
   * setter for user in the Budget class
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * getter for name in the Budget class
   */
  @NonNull
  public String getName() {
    return name;
  }

  /**
   * setter for name in the Budget class
   */
  public void setName(@NonNull String name) {
    this.name = name;
  }

  /**
   * getter for budgetedAmount in the Budget class
   */
  public long getBudgetedAmount() {
    return budgetedAmount;
  }

  /**
   * setter for budgetedAmount in the Budget class
   */
  public void setBudgetedAmount(long budgetedAmount) {
    this.budgetedAmount = budgetedAmount;
  }

  /**
   * getter for startDate in the Budget class
   */
  public Date getStartDate() {
    return startDate;
  }

  /**
   * setter for startDate in the Budget class
   */
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  /**
   * getter for endDate in the Budget class
   */
  public Date getEndDate() {
    return endDate;
  }

  /**
   * setter for endDate in the Budget class
   */
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  /**
   * getter for tresholdPercent in the Budget class
   */
  public double getThresholdPercent() {
    return thresholdPercent;
  }

  /**
   * setter for tresholdPercent in the Budget class
   */
  public void setThresholdPercent(double thresholdPercent) {
    this.thresholdPercent = thresholdPercent;


  }

  /**
   * getter for isRecurring in the Budget class
   */
  public boolean isRecurring() {
    return recurring;
  }


  /**
   * setter for isRecurring in the Budget class
   */
  public void setRecurring(boolean recurring) {
    this.recurring = recurring;
  }
}
