package com.nullspace.multitenant.modules.store.models.entities.shoppingcart;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.product.attribute.ProductAttribute;

import javax.persistence.*;

@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "shopping_cart_attr_items")
public class ShoppingCartAttributeItem extends BaseEntity {
    @Column(name="product_attr_id", nullable=false)
    private Long productAttributeId;

    @Transient
    private ProductAttribute productAttribute;

    @ManyToOne(targetEntity = ShoppingCartItem.class)
    @JoinColumn(name = "shp_cart_item_id", nullable = false)
    private ShoppingCartItem shoppingCartItem;

    public ShoppingCartAttributeItem(ShoppingCartItem shoppingCartItem, ProductAttribute productAttribute) {
        this.shoppingCartItem = shoppingCartItem;
        this.productAttribute = productAttribute;
        this.productAttributeId = productAttribute.getId();
    }

    public ShoppingCartAttributeItem() {
    }

    public ShoppingCartItem getShoppingCartItem() {
        return shoppingCartItem;
    }

    public void setShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        this.shoppingCartItem = shoppingCartItem;
    }

    public void setProductAttributeId(Long productAttributeId) {
        this.productAttributeId = productAttributeId;
    }

    public Long getProductAttributeId() {
        return productAttributeId;
    }

    public void setProductAttribute(ProductAttribute productAttribute) {
        this.productAttribute = productAttribute;
    }

    public ProductAttribute getProductAttribute() {
        return productAttribute;
    }
}
