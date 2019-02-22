package com.nullspace.multitenant.modules.store.models.entities.product.file;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "product_digitals", uniqueConstraints=
@UniqueConstraint(columnNames = {"product_id", "file_name"}))
public class DigitalProduct extends BaseEntity {
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name="file_name",nullable=false)
    private String productFileName;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getProductFileName() {
        return productFileName;
    }

    public void setProductFileName(String productFileName) {
        this.productFileName = productFileName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
