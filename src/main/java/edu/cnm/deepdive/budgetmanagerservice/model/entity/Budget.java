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

@Entity
public class Budget {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "budget_id", nullable = false, updatable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "user_id")
  private Budget budget;

  @NonNull
  @Column(length = 100, nullable = false)
  private String name;

  @NonNull
  @Column(length = 100, nullable = false)
  private Long budgetedAmount;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date startDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date endDate;

  @NonNull
  @Column(length = 100, nullable = false)
  private Double thresholdPercent;
  
}
