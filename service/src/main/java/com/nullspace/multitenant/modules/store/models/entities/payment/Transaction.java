package com.nullspace.multitenant.modules.store.models.entities.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.order.Order;
import org.hibernate.annotations.Type;
import org.json.simple.JSONAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "sm_transactions")
public class Transaction extends BaseEntity implements JSONAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(Transaction.class);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name="transaction_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @Column(name="transaction_type")
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @Column(name="payment_type")
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    @Column(name="details")
    @Type(type = "org.hibernate.type.TextType")
    private String details;

    @Transient
    private Map<String,String> transactionDetails= new HashMap<>();

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;

    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Map<String, String> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(Map<String, String> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    @Override
    public String toJSONString() {
        if(this.getTransactionDetails()!=null && this.getTransactionDetails().size()>0) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.writeValueAsString(this.getTransactionDetails());
            } catch (Exception e) {
                LOGGER.error("Cannot parse transactions map",e);
            }
        }
        return null;
    }
}
