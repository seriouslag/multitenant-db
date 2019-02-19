package com.nullspace.multitenant.demo.modules.store.models.entities.shoppingcart;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.merchant.MerchantStore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "shopping_carts",
        indexes= {
            @Index(name = "shp_cart_code_idx", columnList = "shp_cart_code"),
            @Index(name = "shp_cart_customer_idx", columnList = "customer_id")
        }
)
public class ShoppingCart extends BaseEntity {
    /**
     * Will be used to fetch shopping cart model from the controller
     * this is a unique code that should be attributed from the client (UI)
     *
     */
    @Column(name = "shp_cart_code", unique=true, nullable=false)
    private String shoppingCartCode;

    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "shoppingCart")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shoppingCart")
    private Set<ShoppingCartItem> lineItems = new HashSet<ShoppingCartItem>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchant_id", nullable=false)
    private MerchantStore merchantStore;

    @Column(name = "customer_id", nullable = true)
    private Long customerId;

    @Transient
    private boolean obsolete = false;//when all items are obsolete

    public boolean isObsolete() {
        return obsolete;
    }

    public void setObsolete(boolean obsolete) {
        this.obsolete = obsolete;
    }

    public Set<ShoppingCartItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(Set<ShoppingCartItem> lineItems) {
        this.lineItems = lineItems;
    }

    public String getShoppingCartCode()
    {
        return shoppingCartCode;
    }

    public void setShoppingCartCode( String shoppingCartCode )
    {
        this.shoppingCartCode = shoppingCartCode;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }
}
