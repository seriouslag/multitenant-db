package com.nullspace.multitenant.modules.store.models.entities.product.attribute;

import com.nullspace.multitenant.modules.store.models.entities.common.Description;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="product_option_descs", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "product_option_id",
        })
})
public class ProductOptionDescription extends Description {
    @ManyToOne(targetEntity = ProductOption.class)
    @JoinColumn(name = "product_option_id", nullable = false)
    private ProductOption productOption;

    @Column(name="product_option_comment")
    @Type(type = "org.hibernate.type.TextType")
    private String productOptionComment;

    public ProductOptionDescription() {
    }

    public String getProductOptionComment() {
        return productOptionComment;
    }
    public void setProductOptionComment(String productOptionComment) {
        this.productOptionComment = productOptionComment;
    }

    public ProductOption getProductOption() {
        return productOption;
    }

    public void setProductOption(ProductOption productOption) {
        this.productOption = productOption;
    }
}
