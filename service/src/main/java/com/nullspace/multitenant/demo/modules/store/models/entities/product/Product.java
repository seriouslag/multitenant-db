package com.nullspace.multitenant.demo.modules.store.models.entities.product;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.category.Category;
import com.nullspace.multitenant.demo.modules.store.models.entities.customer.Customer;
import com.nullspace.multitenant.demo.modules.store.models.entities.merchant.MerchantStore;
import com.nullspace.multitenant.demo.modules.store.models.entities.product.attribute.ProductAttribute;
import com.nullspace.multitenant.demo.modules.store.models.entities.product.image.ProductImage;
import com.nullspace.multitenant.demo.modules.store.models.entities.product.manufacturer.Manufacturer;
import com.nullspace.multitenant.demo.modules.store.models.entities.product.relationship.ProductRelationship;
import com.nullspace.multitenant.demo.modules.store.models.entities.tax.taxclass.TaxClass;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "products", uniqueConstraints =
    @UniqueConstraint(columnNames = {"merchant_id", "sku"})
)
public class Product extends BaseEntity {
    private static final long serialVersionUID = -6228066416290007047L;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductDescription> descriptions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductAvailability> availabilities = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductAttribute> attributes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "product")
//cascade is set to remove because product save requires logic to create physical image first and then save the image id in the database, cannot be done in cascade
    private Set<ProductImage> images = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductRelationship> relationships = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id", nullable = false)
    private MerchantStore merchantStore;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "product_categories", joinColumns = {
            @JoinColumn(name = "product_id", nullable = false, updatable = false)}
            ,
            inverseJoinColumns = {@JoinColumn(name = "category_id",
                    nullable = false, updatable = false)}
    )
    @Cascade({
            org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.LOCK,
            org.hibernate.annotations.CascadeType.REFRESH,
            org.hibernate.annotations.CascadeType.REPLICATE
    })
    private Set<Category> categories = new HashSet<>();

    @Column(name = "date_available")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAvailable = new Date();

    @Column(name = "available")
    private boolean available = true;

    @Column(name = "preorder")
    private boolean preOrder = false;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "product_type_id")
    private ProductType type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "tax_class_id")
    private TaxClass taxClass;

    @Column(name = "product_virtual")
    private boolean productVirtual = false;

    @Column(name = "product_ship")
    private boolean productShipeable = false;

    @Column(name = "product_free")
    private boolean productIsFree;

    @Column(name = "product_length")
    private BigDecimal productLength;

    @Column(name = "product_width")
    private BigDecimal productWidth;

    @Column(name = "product_height")
    private BigDecimal productHeight;

    @Column(name = "product_weight")
    private BigDecimal productWeight;

    @Column(name = "review_avg")
    private BigDecimal productReviewAvg;

    @Column(name = "review_count")
    private Integer productReviewCount;

    @Column(name = "quantity_ordered")
    private Integer productOrdered;

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    @Column(name = "sku")
    private String sku;

    /**
     * External system reference SKU/ID
     */
    @Column(name = "ref_sku")
    private String refSku;

    @Column(name = "cond")
    private ProductCondition condition;

    /**
     * RENTAL ADDITIONAL FIELDS
     */

    @Column(name = "rental_status")
    private RentalStatus rentalStatus;


    @Column(name = "rental_duration")
    private Integer rentalDuration;

    @Column(name = "rental_period")
    private Integer rentalPeriod;


    public Integer getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(Integer rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public Integer getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    /**
     * End rental fields
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer owner;

    public Product() {
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public boolean isProductVirtual() {
        return productVirtual;
    }

    public BigDecimal getProductLength() {
        return productLength;
    }

    public void setProductLength(BigDecimal productLength) {
        this.productLength = productLength;
    }

    public BigDecimal getProductWidth() {
        return productWidth;
    }

    public void setProductWidth(BigDecimal productWidth) {
        this.productWidth = productWidth;
    }

    public BigDecimal getProductHeight() {
        return productHeight;
    }

    public void setProductHeight(BigDecimal productHeight) {
        this.productHeight = productHeight;
    }

    public BigDecimal getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(BigDecimal productWeight) {
        this.productWeight = productWeight;
    }

    public BigDecimal getProductReviewAvg() {
        return productReviewAvg;
    }

    public void setProductReviewAvg(BigDecimal productReviewAvg) {
        this.productReviewAvg = productReviewAvg;
    }

    public Integer getProductReviewCount() {
        return productReviewCount;
    }

    public void setProductReviewCount(Integer productReviewCount) {
        this.productReviewCount = productReviewCount;
    }

    public Integer getProductOrdered() {
        return productOrdered;
    }

    public void setProductOrdered(Integer productOrdered) {
        this.productOrdered = productOrdered;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Set<ProductDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<ProductDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public boolean getProductVirtual() {
        return productVirtual;
    }

    public void setProductVirtual(boolean productVirtual) {
        this.productVirtual = productVirtual;
    }

    public boolean getProductIsFree() {
        return productIsFree;
    }

    public void setProductIsFree(boolean productIsFree) {
        this.productIsFree = productIsFree;
    }

    public Set<ProductAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<ProductAttribute> attributes) {
        this.attributes = attributes;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Set<ProductAvailability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(Set<ProductAvailability> availabilities) {
        this.availabilities = availabilities;
    }

    public TaxClass getTaxClass() {
        return taxClass;
    }

    public void setTaxClass(TaxClass taxClass) {
        this.taxClass = taxClass;
    }

    public Set<ProductImage> getImages() {
        return images;
    }

    public void setImages(Set<ProductImage> images) {
        this.images = images;
    }

    public Set<ProductRelationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(Set<ProductRelationship> relationships) {
        this.relationships = relationships;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }

    public Date getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(Date dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isProductShipeable() {
        return productShipeable;
    }

    public void setProductShipeable(boolean productShipeable) {
        this.productShipeable = productShipeable;
    }

    public ProductDescription getProductDescription() {
        if (this.getDescriptions() != null && this.getDescriptions().size() > 0) {
            return this.getDescriptions().iterator().next();
        }
        return null;
    }

    public ProductImage getProductImage() {
        ProductImage productImage = null;
        if (this.getImages() != null && this.getImages().size() > 0) {
            for (ProductImage image : this.getImages()) {
                productImage = image;
                if (productImage.isDefaultImage()) {
                    break;
                }
            }
        }
        return productImage;
    }

    public boolean isPreOrder() {
        return preOrder;
    }

    public void setPreOrder(boolean preOrder) {
        this.preOrder = preOrder;
    }

    public String getRefSku() {
        return refSku;
    }

    public void setRefSku(String refSku) {
        this.refSku = refSku;
    }

    public ProductCondition getCondition() {
        return condition;
    }

    public void setCondition(ProductCondition condition) {
        this.condition = condition;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
}
