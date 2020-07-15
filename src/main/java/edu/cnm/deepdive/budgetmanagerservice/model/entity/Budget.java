package edu.cnm.deepdive.budgetmanagerservice.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.cnm.deepdive.budgetmanagerservice.view.FlatBudget;
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

@SuppressWarnings("JpaDataSourceORMInspection")

// TODO Ask about JavaDocs, and what goes below.
/**
 * This is a table that keeps track of the users budgets and stores the information in Tables.
 * The Tables may be fetched or edited by using the following commands.
 *
 * getUser() - gets the
 *
 *
 * budget_id,
 * user_id, budget, name, budgeted amount, start date, end date,
 * threshold percent, recurring.
 *
 * the class
 */
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


  public long getBudgetedAmount() {
    return budgetedAmount;
  }

  public void setBudgetedAmount(long budgetedAmount) {
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

  public double getThresholdPercent() {
    return thresholdPercent;
  }

  public void setThresholdPercent(double thresholdPercent) {
    this.thresholdPercent = thresholdPercent;


  }

  public boolean isRecurring() { return recurring; }

  public void setRecurring(boolean recurring) {
    this.recurring = recurring;
  }
}
