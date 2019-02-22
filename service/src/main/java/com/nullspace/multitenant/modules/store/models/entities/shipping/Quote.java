package com.nullspace.multitenant.modules.store.models.entities.shipping;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.common.Delivery;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="shipping_quotes")
public class Quote extends BaseEntity {
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "module", nullable = false)
    private String module;

    @Column(name = "option_name")
    private String optionName = null;

    @Column(name = "option_code")
    private String optionCode = null;

    @Temporal(TemporalType.TIMESTAMP)
    @Column (name ="option_delivery_date")
    private Date optionDeliveryDate = null;

    @Temporal(TemporalType.TIMESTAMP)
    @Column (name ="option_shipping_date")
    private Date optionShippingDate = null;

    @Temporal(TemporalType.TIMESTAMP)
    @Column (name ="quote_date")
    private Date quoteDate;

    @Column(name = "shipping_number_days")
    private Integer estimatedNumberOfDays;

    @Column (name ="quote_price")
    private BigDecimal price = null;

    @Column (name ="quote_handling")
    private BigDecimal handling = null;

    @Column(name = "free_shipping")
    private boolean freeShipping;

    @Embedded
    private Delivery delivery = null;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionCode() {
        return optionCode;
    }

    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }

    public Date getOptionDeliveryDate() {
        return optionDeliveryDate;
    }

    public void setOptionDeliveryDate(Date optionDeliveryDate) {
        this.optionDeliveryDate = optionDeliveryDate;
    }

    public Date getOptionShippingDate() {
        return optionShippingDate;
    }

    public void setOptionShippingDate(Date optionShippingDate) {
        this.optionShippingDate = optionShippingDate;
    }

    public Date getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }

    public Integer getEstimatedNumberOfDays() {
        return estimatedNumberOfDays;
    }

    public void setEstimatedNumberOfDays(Integer estimatedNumberOfDays) {
        this.estimatedNumberOfDays = estimatedNumberOfDays;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public BigDecimal getHandling() {
        return handling;
    }

    public void setHandling(BigDecimal handling) {
        this.handling = handling;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
