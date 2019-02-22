package com.nullspace.multitenant.modules.store.models.entities.product;

import com.nullspace.multitenant.modules.store.models.entities.common.Description;

import javax.persistence.*;

@Entity
@Table(name = "product_descriptions", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "product_id",
        })
})
public class ProductDescription extends Description {
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "product_highlight")
    private String productHighlight;

    @Column(name = "download_lnk")
    private String productExternalDl;

    @Column(name = "sef_url")
    private String seUrl;

    @Column(name = "meta_title")
    private String metatagTitle;

    @Column(name = "meta_keywords")
    private String metatagKeywords;

    @Column(name = "meta_description")
    private String metatagDescription;

    public ProductDescription() {
    }

    public String getProductHighlight() {
        return productHighlight;
    }

    public void setProductHighlight(String productHighlight) {
        this.productHighlight = productHighlight;
    }

    public String getProductExternalDl() {
        return productExternalDl;
    }

    public void setProductExternalDl(String productExternalDl) {
        this.productExternalDl = productExternalDl;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}