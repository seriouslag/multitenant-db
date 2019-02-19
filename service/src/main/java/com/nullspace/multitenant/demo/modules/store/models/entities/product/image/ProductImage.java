package com.nullspace.multitenant.demo.modules.store.models.entities.product.image;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;
import com.nullspace.multitenant.demo.modules.store.models.entities.product.Product;

import javax.persistence.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_images")
public class ProductImage extends BaseEntity {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productImage", cascade = CascadeType.ALL)
    private List<ProductImageDescription> descriptions = new ArrayList<ProductImageDescription>();


    @Column(name = "product_image")
    private String productImage;

    @Column(name = "default_image")
    private boolean defaultImage = true;

    /**
     * default to 0 for images managed by the system
     */
    @Column(name = "image_type")
    private int imageType;

    /**
     * Refers to images not accessible through the system. It may also be a video.
     */
    @Column(name = "product_image_url")
    private String productImageUrl;


    @Column(name = "image_crop")
    private boolean imageCrop;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Transient
    private InputStream image = null;

    //private MultiPartFile image

    public ProductImage(){
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public boolean isDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(boolean defaultImage) {
        this.defaultImage = defaultImage;
    }

    public int getImageType() {
        return imageType;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    public boolean isImageCrop() {
        return imageCrop;
    }

    public void setImageCrop(boolean imageCrop) {
        this.imageCrop = imageCrop;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setDescriptions(List<ProductImageDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public List<ProductImageDescription> getDescriptions() {
        return descriptions;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }
}
