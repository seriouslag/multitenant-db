package com.nullspace.multitenant.modules.store.models.entities.merchant;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.constants.MeasureUnit;
import com.nullspace.multitenant.modules.store.models.entities.reference.country.Country;
import com.nullspace.multitenant.modules.store.models.entities.reference.currency.Currency;
import com.nullspace.multitenant.modules.store.models.entities.reference.zone.Zone;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "merchant_stores")
public class MerchantStore extends BaseEntity {
    public final static String DEFAULT_STORE = "default";

    @NotEmpty
    @Column(name = "store_name", nullable=false, length=100)
    private String storename;

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9_]*$")
    @Column(name = "store_code", nullable=false, unique=true, length=100)
    private String code;

    @NotEmpty
    @Column(name = "store_phone", length=50)
    private String storephone;

    @Column(name = "store_address")
    private String storeaddress;

    @NotEmpty
    @Column(name = "store_city", length=100)
    private String storecity;

    @NotEmpty
    @Column(name = "store_postal_code", length=15)
    private String storepostalcode;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
    @JoinColumn(name="country_id", nullable=false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Zone.class)
    @JoinColumn(name="zone_id")
    private Zone zone;

    @Column(name = "store_state_prov", length=100)
    private String storestateprovince;

    @Column(name = "weightunitcode", length=5)
    private String weightunitcode = MeasureUnit.LB.name();

    @Column(name = "seizeunitcode", length=5)
    private String seizeunitcode = MeasureUnit.IN.name();

    @Temporal(TemporalType.DATE)
    @Column(name = "in_business_since")
    private Date inBusinessSince = new Date();

    @Transient
    private String dateBusinessSince;

    @Column(name = "use_cache")
    private boolean useCache = false;

    @Column(name="store_template", length=25)
    private String storeTemplate;

    @Column(name="invoice_template", length=25)
    private String invoiceTemplate;

    @Column(name="domain_name", length=80)
    private String domainName;

    @Column(name="continueshoppingurl", length=150)
    private String continueshoppingurl;

    @Email
    @NotEmpty
    @Column(name = "store_email", length=60, nullable=false)
    private String storeEmailAddress;

    @Column(name="STORE_LOGO", length=100)
    private String storeLogo;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Currency.class)
    @JoinColumn(name = "CURRENCY_ID", nullable=false)
    private Currency currency;

    @Column(name = "CURRENCY_FORMAT_NATIONAL")
    private boolean currencyFormatNational;

    public MerchantStore() {
    }

    public boolean isUseCache() {
        return useCache;
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getStorephone() {
        return storephone;
    }

    public void setStorephone(String storephone) {
        this.storephone = storephone;
    }

    public String getStoreaddress() {
        return storeaddress;
    }

    public void setStoreaddress(String storeaddress) {
        this.storeaddress = storeaddress;
    }

    public String getStorecity() {
        return storecity;
    }

    public void setStorecity(String storecity) {
        this.storecity = storecity;
    }

    public String getStorepostalcode() {
        return storepostalcode;
    }

    public void setStorepostalcode(String storepostalcode) {
        this.storepostalcode = storepostalcode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public String getStorestateprovince() {
        return storestateprovince;
    }

    public void setStorestateprovince(String storestateprovince) {
        this.storestateprovince = storestateprovince;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getWeightunitcode() {
        return weightunitcode;
    }

    public void setWeightunitcode(String weightunitcode) {
        this.weightunitcode = weightunitcode;
    }

    public String getSeizeunitcode() {
        return seizeunitcode;
    }

    public void setSeizeunitcode(String seizeunitcode) {
        this.seizeunitcode = seizeunitcode;
    }

    public Date getInBusinessSince() {
        return (Date)inBusinessSince.clone();
    }

    public void setInBusinessSince(Date inBusinessSince) {
        this.inBusinessSince = (Date)inBusinessSince.clone();
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public String getStoreTemplate() {
        return storeTemplate;
    }

    public void setStoreTemplate(String storeTemplate) {
        this.storeTemplate = storeTemplate;
    }

    public String getInvoiceTemplate() {
        return invoiceTemplate;
    }

    public void setInvoiceTemplate(String invoiceTemplate) {
        this.invoiceTemplate = invoiceTemplate;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getContinueshoppingurl() {
        return continueshoppingurl;
    }

    public void setContinueshoppingurl(String continueshoppingurl) {
        this.continueshoppingurl = continueshoppingurl;
    }

    public String getStoreEmailAddress() {
        return storeEmailAddress;
    }

    public void setStoreEmailAddress(String storeEmailAddress) {
        this.storeEmailAddress = storeEmailAddress;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDateBusinessSince(String dateBusinessSince) {
        this.dateBusinessSince = dateBusinessSince;
    }

    public String getDateBusinessSince() {
        return dateBusinessSince;
    }

    public void setCurrencyFormatNational(boolean currencyFormatNational) {
        this.currencyFormatNational = currencyFormatNational;
    }

    public boolean isCurrencyFormatNational() {
        return currencyFormatNational;
    }
}
