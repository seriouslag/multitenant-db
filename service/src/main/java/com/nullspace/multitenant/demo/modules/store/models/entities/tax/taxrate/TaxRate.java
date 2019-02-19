package com.nullspace.multitenant.demo.modules.store.models.entities.tax.taxrate;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.merchant.MerchantStore;
import com.nullspace.multitenant.demo.modules.store.models.entities.reference.country.Country;
import com.nullspace.multitenant.demo.modules.store.models.entities.reference.zone.Zone;
import com.nullspace.multitenant.demo.modules.store.models.entities.tax.taxclass.TaxClass;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "tax_rates", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "tax_code",
                "merchant_id"
        })
})
public class TaxRate extends BaseEntity {
    @Column(name = "tax_priority")
    private Integer taxPriority = 0;

    @Column(name = "tax_rate" , nullable= false , precision=7, scale=4)
    private BigDecimal taxRate;

    @NotEmpty
    @Column(name = "tax_code")
    private String code;

    @Column(name = "piggyback")
    private boolean piggyback;

    @ManyToOne
    @JoinColumn(name = "tax_class_id" , nullable=false)
    private TaxClass taxClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchant_id", nullable=false)
    private MerchantStore merchantStore;

    @Valid
    @OneToMany(mappedBy = "taxRate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TaxRateDescription> descriptions = new ArrayList<TaxRateDescription>();

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
    @JoinColumn(name="country_id", nullable=false, updatable=true)
    private Country country;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="zone_id", nullable=true, updatable=true)
    private Zone zone;

    @Column(name = "store_state_prov", length=100)
    private String stateProvince;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TaxRate parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TaxRate> taxRates = new ArrayList<TaxRate>();

    @Transient
    private String rateText;

    public String getRateText() {
        return rateText;
    }

    public void setRateText(String rateText) {
        this.rateText = rateText;
    }

    public TaxRate() {
    }

    public Integer getTaxPriority() {
        return taxPriority;
    }

    public void setTaxPriority(Integer taxPriority) {
        this.taxPriority = taxPriority;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public boolean isPiggyback() {
        return piggyback;
    }

    public void setPiggyback(boolean piggyback) {
        this.piggyback = piggyback;
    }

    public TaxClass getTaxClass() {
        return taxClass;
    }

    public void setTaxClass(TaxClass taxClass) {
        this.taxClass = taxClass;
    }

    public List<TaxRateDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<TaxRateDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Zone getZone() {
        return zone;
    }

    public void setTaxRates(List<TaxRate> taxRates) {
        this.taxRates = taxRates;
    }

    public List<TaxRate> getTaxRates() {
        return taxRates;
    }

    public void setParent(TaxRate parent) {
        this.parent = parent;
    }

    public TaxRate getParent() {
        return parent;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
