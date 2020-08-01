package edu.cnm.deepdive.budgetmanagerservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.cnm.deepdive.budgetmanagerservice.view.FlatTransaction;
import java.net.URI;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


/**
 * An entity class that holds transaction_id as PK, budget_id as FK, date, amount, name, note and created.
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Component
@JsonIgnoreProperties(
    value = {"id", "budget", "date", "amount", "note", "created"},
    allowGetters = true,
    ignoreUnknown = true
)
public class Transaction implements FlatTransaction {

  private static EntityLinks entityLinks;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "transaction_id", nullable = false, updatable = false)
  private Long id;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @ManyToOne(fetch = FetchType.EAGER,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "budget_id", nullable = false)
  private Budget budget;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date date;

  @Column(length = 100, nullable = false)
  private long amount;

  @NonNull
  @Column(length = 100, nullable = false)
  private String name;

  @Column(length = 1000)
  private String note;




  /**
   * getter for id in the Transaction class
   */
  public Long getId() {
    return id;
  }

  /**
   * getter for budget in the Transaction class
   */
  public Budget getBudget() {
    return budget;
  }

  /**
   * setter for budget in the Transaction class
   */
  public void setBudget(Budget budget) {
    this.budget = budget;
  }

  /**
   * getter for date in the Transaction class
   */
  public Date getDate() {
    return date;
  }

  /**
   * setter for date in the Transaction class
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * getter for amount in the Transaction class
   */
  public Long getAmount() {
    return amount;
  }

  /**
   * setter for amount in the Transaction class
   */
  public void setAmount(long amount) {
    this.amount = amount;
  }

  /**
   * getter for name in the Transaction class
   */
  @NonNull
  public String getName() {
    return name;
  }

  /**
   * setter for name in the Transaction class
   */
  public void setName(@NonNull String name) {
    this.name = name;
  }

  /**
   * getter for note in the Transaction class
   */
  public String getNote() {
    return note;
  }

  /**
   * setter for note in the Transaction class
   */
  public void setNote(String note) {
    this.note = note;
  }

  /**
   * getter for created in the Transaction class
   */
  public Date getCreated() {
    return created;
  }

  @PostConstruct
  private void initHateoas() {
    entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(
      @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") EntityLinks entityLinks) {
    Transaction.entityLinks = entityLinks;
  }

  @Override
  public URI getHref() {
    return (id != null) ? entityLinks.linkForItemResource(Transaction.class, id).toUri() : null;
  }
}

