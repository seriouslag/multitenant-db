package com.nullspace.multitenant.modules.store.models.entities.shoppingcart;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.product.Product;
import com.nullspace.multitenant.modules.store.models.entities.product.price.FinalPrice;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "shopping_cart_items")
public class ShoppingCartItem extends BaseEntity {
    @ManyToOne(targetEntity = ShoppingCart.class)
    @JoinColumn(name = "shp_cart_id", nullable = false)
    private ShoppingCart shoppingCart;

    @Column(name="quantity")
    private Integer quantity = 1;

    @Column(name="product_id", nullable=false)
    private Long productId;

    @Transient
    private boolean productVirtual;

    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "shoppingCartItem")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shoppingCartItem")
    private Set<ShoppingCartAttributeItem> attributes = new HashSet<>();

    @Transient
    private BigDecimal itemPrice;//item final price including all rebates

    @Transient
    private BigDecimal subTotal;//item final price * quantity

    @Transient
    private FinalPrice finalPrice;//contains price details (raw prices)

    @Transient
    private Product product;

    @Transient
    private boolean obsolete = false;

    public ShoppingCartItem(ShoppingCart shoppingCart, Product product) {
        this.product = product;
        this.productId = product.getId();
        this.quantity = 1;
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCartItem(Product product) {
        this.product = product;
        this.productId = product.getId();
        this.quantity = 1;
    }

    public ShoppingCartItem() {
    }

    public void setAttributes(Set<ShoppingCartAttributeItem> attributes) {
/*	    if(attributes==null) {
	    	this.attributes = null;
	    	return;
	    }
		this.attributes.clear();
	    this.attributes.addAll( attributes );*/
        this.attributes = attributes;
    }

    public Set<ShoppingCartAttributeItem> getAttributes() {
        return attributes;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void addAttributes(ShoppingCartAttributeItem shoppingCartAttributeItem)
    {
        this.attributes.add(shoppingCartAttributeItem);
    }

    public void removeAttributes(ShoppingCartAttributeItem shoppingCartAttributeItem)
    {
        this.attributes.remove(shoppingCartAttributeItem);
    }

    public void removeAllAttributes(){
        this.attributes.removeAll(Collections.EMPTY_SET);
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setFinalPrice(FinalPrice finalPrice) {
        this.finalPrice = finalPrice;
    }

    public FinalPrice getFinalPrice() {
        return finalPrice;
    }

    public boolean isObsolete() {
        return obsolete;
    }

    public void setObsolete(boolean obsolete) {
        this.obsolete = obsolete;
    }

    public boolean isProductVirtual() {
        return productVirtual;
    }

    public void setProductVirtual(boolean productVirtual) {
        this.productVirtual = productVirtual;
    }
}
