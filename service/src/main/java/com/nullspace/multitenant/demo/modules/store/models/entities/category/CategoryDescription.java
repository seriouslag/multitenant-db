package com.nullspace.multitenant.demo.modules.store.models.entities.category;

import com.nullspace.multitenant.demo.modules.store.models.entities.common.Description;

import javax.persistence.*;

@Entity
@Table(name="category_descriptions", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "category_id",
        })
})
public class CategoryDescription extends Description {
    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name="sef_url", length=120)
    private String seUrl;

    @Column(name = "category_highlight")
    private String categoryHighlight;

    public String getCategoryHighlight() {
        return categoryHighlight;
    }

    public void setCategoryHighlight(String categoryHighlight) {
        this.categoryHighlight = categoryHighlight;
    }

    @Column(name="meta_title", length=120)
    private String metatagTitle;

    @Column(name="meta_keywords")
    private String metatagKeywords;

    @Column(name="meta_description")
    private String metatagDescription;

    public CategoryDescription() {
    }

    public CategoryDescription(String name) {
        this.setName(name);
    }

    public String getSeUrl() {
        return seUrl;
    }

    public void setSeUrl(String seUrl) {
        this.seUrl = seUrl;
    }

    public String getMetatagTitle() {
        return metatagTitle;
    }

    public void setMetatagTitle(String metatagTitle) {
        this.metatagTitle = metatagTitle;
    }

    public String getMetatagKeywords() {
        return metatagKeywords;
    }

    public void setMetatagKeywords(String metatagKeywords) {
        this.metatagKeywords = metatagKeywords;
    }

    public String getMetatagDescription() {
        return metatagDescription;
    }

    public void setMetatagDescription(String metatagDescription) {
        this.metatagDescription = metatagDescription;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
