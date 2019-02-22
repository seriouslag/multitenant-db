package com.nullspace.multitenant.modules.store.models.entities.order;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.common.Billing;
import com.nullspace.multitenant.modules.store.models.entities.common.Delivery;
import com.nullspace.multitenant.modules.store.models.entities.merchant.MerchantStore;
import com.nullspace.multitenant.modules.store.models.entities.order.attributes.OrderAttribute;
import com.nullspace.multitenant.modules.store.models.entities.order.orderproduct.OrderProduct;
import com.nullspace.multitenant.modules.store.models.entities.order.orderstatus.OrderStatus;
import com.nullspace.multitenant.modules.store.models.entities.order.orderstatus.OrderStatusHistory;
import com.nullspace.multitenant.modules.store.models.entities.order.payment.CreditCard;
import com.nullspace.multitenant.modules.store.models.entities.payment.PaymentType;
import com.nullspace.multitenant.modules.store.models.entities.reference.currency.Currency;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order extends BaseEntity {
    @Column (name ="order_status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column (name ="last_modified")
    private Date lastModified;

    //the customer object can be detached. An order can exist and the customer deleted
    @Column (name ="customer_id")
    private Long customerId;

    @Temporal(TemporalType.DATE)
    @Column (name ="date_purchased")
    private Date datePurchased;

    //used for an order payable on multiple installment
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name ="order_date_finished")
    private Date orderDateFinished;

    //What was the exchange rate
    @Column (name ="currency_value")
    private BigDecimal currencyValue = new BigDecimal(1);//default 1-1

    @Column (name ="order_total")
    private BigDecimal total;

    @Column (name ="ip_address")
    private String ipAddress;

    @Column (name ="channel")
    @Enumerated(value = EnumType.STRING)
    private OrderChannel channel;

    @Column (name ="order_type")
    @Enumerated(value = EnumType.STRING)
    private OrderType orderType = OrderType.ORDER;

    @Column (name ="payment_type")
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    @Column (name ="payment_module_code")
    private String paymentModuleCode;

    @Column (name ="shipping_module_code")
    private String shippingModuleCode;

    @Column(name = "customer_agreed")
    private Boolean customerAgreement = false;

    @Column(name = "confirmed_address")
    private Boolean confirmedAddress = false;

    @Embedded
    private Delivery delivery = null;

    @Valid
    @Embedded
    private Billing billing = null;

    @Embedded
    private CreditCard creditCard = null;

    @ManyToOne(targetEntity = Currency.class)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Type(type="locale")
    @Column (name ="locale")
    private Locale locale;

    @ManyToOne(targetEntity = MerchantStore.class)
    @JoinColumn(name="merchant_id")
    private MerchantStore merchant;

    //@OneToMany(mappedBy = "order")
    //private Set<OrderAccount> orderAccounts = new HashSet<OrderAccount>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProducts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @OrderBy("sort_order asc")
    private Set<OrderTotal> orderTotal = new LinkedHashSet<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @OrderBy("order_status_history_id asc")
    private Set<OrderStatusHistory> orderHistory = new LinkedHashSet<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderAttribute> orderAttributes = new LinkedHashSet<>();

    public Order() {
    }

    @Column(name ="customer_email_address", length=50, nullable=false)
    private String customerEmailAddress;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getLastModified() {
        return (Date)lastModified.clone();
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = (Date)lastModified.clone();
    }

    public Date getDatePurchased() {
        return (Date)datePurchased.clone();
    }

    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = (Date)datePurchased.clone();
    }

    public Date getOrderDateFinished() {
        return (Date)orderDateFinished.clone();
    }

    public void setOrderDateFinished(Date orderDateFinished) {
        this.orderDateFinished = (Date)orderDateFinished.clone();
    }

    public BigDecimal getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(BigDecimal currencyValue) {
        this.currencyValue = currencyValue;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPaymentModuleCode() {
        return paymentModuleCode;
    }

    public void setPaymentModuleCode(String paymentModuleCode) {
        this.paymentModuleCode = paymentModuleCode;
    }

    public String getShippingModuleCode() {
        return shippingModuleCode;
    }

    public void setShippingModuleCode(String shippingModuleCode) {
        this.shippingModuleCode = shippingModuleCode;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public MerchantStore getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantStore merchant) {
        this.merchant = merchant;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Set<OrderTotal> getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Set<OrderTotal> orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Set<OrderStatusHistory> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(Set<OrderStatusHistory> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public Billing getBilling() {
        return billing;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    public void setChannel(OrderChannel channel) {
        this.channel = channel;
    }

    public OrderChannel getChannel() {
        return channel;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Boolean getCustomerAgreement() {
        return customerAgreement;
    }

    public void setCustomerAgreement(Boolean customerAgreement) {
        this.customerAgreement = customerAgreement;
    }

    public Boolean getConfirmedAddress() {
        return confirmedAddress;
    }

    public void setConfirmedAddress(Boolean confirmedAddress) {
        this.confirmedAddress = confirmedAddress;
    }

    public Set<OrderAttribute> getOrderAttributes() {
        return orderAttributes;
    }

    public void setOrderAttributes(Set<OrderAttribute> orderAttributes) {
        this.orderAttributes = orderAttributes;
    }
}
