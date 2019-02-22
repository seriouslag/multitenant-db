package com.nullspace.multitenant.modules.store.models.entities.category;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.merchant.MerchantStore;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Entity
//@EntityListeners(value = AuditListener.class)
@Table(name = "categories", uniqueConstraints=
    @UniqueConstraint(columnNames = {"merchant_id", "code"})
)
public class Category extends BaseEntity {

//    @Embedded
//    private AuditSection auditSection = new AuditSection();

    @Valid
    @OneToMany(mappedBy="category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CategoryDescription> descriptions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchant_id", nullable=false)
    private MerchantStore merchantStore;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>();

    @Column(name = "category_image", length=100)
    private String categoryImage;

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @Column(name = "category_status")
    private boolean categoryStatus;

    @Column(name = "visible")
    private boolean visible;

    @Column(name = "depth")
    private Integer depth;

    @Column(name = "lineage")
    private String lineage;

    @Column(name="featured")
    private boolean featured;

    @NotEmpty
    @Column(name="code", length=100, nullable=false)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Category() {
    }

    public Category(MerchantStore store) {
        this.merchantStore = store;
        this.id = 0L;
    }

    public List<CategoryDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<CategoryDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public String getLineage() {
        return lineage;
    }

    public void setLineage(String lineage) {
        this.lineage = lineage;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public CategoryDescription getDescription() {
        if(descriptions!=null && descriptions.size()>0) {
            return descriptions.iterator().next();
        }

        return null;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }
}
