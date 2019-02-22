package com.nullspace.multitenant.modules.store.models.entities.product;

import com.nullspace.multitenant.models.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "product_types")
public class ProductType extends BaseEntity {
    @Column(name = "prd_type_code")
    private String code;

    @Column(name = "prd_type_add_to_cart")
    private Boolean allowAddToCart;

    public ProductType() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAllowAddToCart() {
        return allowAddToCart;
    }

    public void setAllowAddToCart(boolean allowAddToCart) {
        this.allowAddToCart = allowAddToCart;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
