package com.nullspace.multitenant.modules.store.models.entities.product.relationship;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.merchant.MerchantStore;
import com.nullspace.multitenant.modules.store.models.entities.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "product_relationships")
public class ProductRelationship extends BaseEntity {
    @ManyToOne(targetEntity = MerchantStore.class)
    @JoinColumn(name="merchant_id",nullable=false)
    private MerchantStore store;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name="product_id",updatable=false)
    private Product product = null;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name="related_product_id",updatable=false)
    private Product relatedProduct = null;

    @Column(name="code")
    private String code;

    @Column(name="active")
    private boolean active = true;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getRelatedProduct() {
        return relatedProduct;
    }

    public void setRelatedProduct(Product relatedProduct) {
        this.relatedProduct = relatedProduct;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ProductRelationship() {
    }

    public MerchantStore getStore() {
        return store;
    }

    public void setStore(MerchantStore store) {
        this.store = store;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
