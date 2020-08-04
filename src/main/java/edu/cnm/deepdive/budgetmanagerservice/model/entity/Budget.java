package edu.cnm.deepdive.budgetmanagerservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.cnm.deepdive.budgetmanagerservice.view.FlatBudget;
import edu.cnm.deepdive.budgetmanagerservice.view.FlatTransaction;
import java.net.URI;
import java.time.LocalDate;
import java.util.Date;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


/**
 * An entity class that holds budget_id as PK, user_id as FK, name, budget_amount, start_date,
 * end_date, treshold_percent and recurring.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Component
@JsonIgnoreProperties(
    value = {"id", "created", "updated", "thresholdPercent"},
    allowGetters = true,
    ignoreUnknown = true
)
public class Budget implements FlatBudget {

  private static EntityLinks entityLinks;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "budget_id", nullable = false, updatable = false)
  private Long id;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date updated;

  @ManyToOne(fetch = FetchType.EAGER,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
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

  @Column(nullable = false)
  private Long budgetedAmount;

  //  @Temporal(TemporalType.DATE)
  @Column(nullable = false, updatable = false, columnDefinition = "DATE")
  private LocalDate startDate;

  //  @Temporal(TemporalType.DATE)
  @Column(nullable = false, columnDefinition = "DATE")
  private LocalDate endDate;

  // TODO should be retrieved from a query.
  private double thresholdPercent;

  private Boolean recurring;

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


  @Override
  public Date getCreated() {
    return created;
  }

  @Override
  public Date getUpdated() {
    return updated;
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
  public Long getBudgetedAmount() {
    return budgetedAmount;
  }

  /**
   * setter for budgetedAmount in the Budget class
   */
  public void setBudgetedAmount(Long budgetedAmount) {
    this.budgetedAmount = budgetedAmount;
  }

  /**
   * getter for startDate in the Budget class
   */
  public LocalDate getStartDate() {
    return startDate;
  }

  /**
   * setter for startDate in the Budget class
   */
  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  /**
   * getter for endDate in the Budget class
   */
  public LocalDate getEndDate() {
    return endDate;
  }

  /**
   * setter for endDate in the Budget class
   */
  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  /**
   * getter for tresholdPercent in the Budget class
   */
  public Double getThresholdPercent() {
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
  public Boolean isRecurring() {
    return recurring;
  }


  /**
   * setter for isRecurring in the Budget class
   */
  public void setRecurring(Boolean recurring) {
    this.recurring = recurring;
  }

  @PostConstruct
  private void initHateoas() {
    entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(
      @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") EntityLinks entityLinks) {
    Budget.entityLinks = entityLinks;
  }

  @Override
  public URI getHref() {
    return (id != null) ? entityLinks.linkForItemResource(Budget.class, id).toUri() : null;
  }


}
