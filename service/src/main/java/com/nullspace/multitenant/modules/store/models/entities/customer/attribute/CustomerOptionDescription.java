package com.nullspace.multitenant.modules.store.models.entities.customer.attribute;

import com.nullspace.multitenant.models.entities.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="customer_option_descs", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "customer_option_id",
        })
})
public class CustomerOptionDescription extends BaseEntity {
    @ManyToOne(targetEntity = CustomerOption.class)
    @JoinColumn(name = "customer_option_id", nullable = false)
    private CustomerOption customerOption;

    @Column(name="customer_option_comment")
    @Type(type = "org.hibernate.type.TextType")
    private String customerOptionComment;

    public CustomerOptionDescription() {
    }

    public CustomerOption getCustomerOption() {
        return customerOption;
    }

    public void setCustomerOption(CustomerOption customerOption) {
        this.customerOption = customerOption;
    }

    public String getCustomerOptionComment() {
        return customerOptionComment;
    }

    public void setCustomerOptionComment(String customerOptionComment) {
        this.customerOptionComment = customerOptionComment;
    }
}
