package com.nullspace.multitenant.demo.modules.store.models.entities.tax.taxclass;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.merchant.MerchantStore;
import com.nullspace.multitenant.demo.modules.store.models.entities.product.Product;
import com.nullspace.multitenant.demo.modules.store.models.entities.tax.taxrate.TaxRate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tax_classes",uniqueConstraints=
@UniqueConstraint(columnNames = {"merchant_id", "tax_class_code"}) )
public class TaxClass extends BaseEntity {
    public final static String DEFAULT_TAX_CLASS = "DEFAULT";
    public TaxClass(String code) {
        this.code = code;
        this.title = code;
    }

    @NotEmpty
    @Column(name="tax_class_code", nullable=false, length=10)
    private String code;

    @NotEmpty
    @Column(name = "tax_class_title" , nullable=false , length=32 )
    private String title;

    @OneToMany(mappedBy = "taxClass", targetEntity = Product.class)
    private List<Product> products = new ArrayList<Product>();


/*	@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "MERCHANT_TAXCLASSES", schema=SchemaConstant.SALESMANAGER_SCHEMA, joinColumns = {
			@JoinColumn(name = "TAX_CLASS_ID", nullable = false) },
			inverseJoinColumns = { @JoinColumn(name = "MERCHANT_ID",
					nullable = false) })
	private Set<MerchantStore> stores = new HashSet<MerchantStore>();*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchant_id", nullable=true)
    private MerchantStore merchantStore;

    @OneToMany(mappedBy = "taxClass")
    private List<TaxRate> taxRates = new ArrayList<TaxRate>();

    public TaxClass() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TaxRate> getTaxRates() {
        return taxRates;
    }

    public void setTaxRates(List<TaxRate> taxRates) {
        this.taxRates = taxRates;
    }

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }
}
