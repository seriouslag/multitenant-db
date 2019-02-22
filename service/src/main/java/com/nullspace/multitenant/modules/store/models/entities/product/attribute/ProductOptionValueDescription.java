package com.nullspace.multitenant.modules.store.models.entities.product.attribute;

import com.nullspace.multitenant.modules.store.models.entities.common.Description;

import javax.persistence.*;

@Entity
@Table(name = "product_option_value_descriptions", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "product_option_value_id",
        })
}
)
public class ProductOptionValueDescription extends Description {
    @ManyToOne(targetEntity = ProductOptionValue.class)
    @JoinColumn(name = "product_option_value_id")
    private ProductOptionValue productOptionValue;

    public ProductOptionValueDescription() {
    }

    public ProductOptionValue getProductOptionValue() {
        return productOptionValue;
    }

    public void setProductOptionValue(ProductOptionValue productOptionValue) {
        this.productOptionValue = productOptionValue;
    }
}
