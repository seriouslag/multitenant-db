package com.nullspace.multitenant.demo.modules.store.models.entities.order.orderproduct;

import com.nullspace.multitenant.demo.models.entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="order_product_downloads")
public class OrderProductDownload extends BaseEntity {
    public final static int DEFAULT_DOWNLOAD_MAX_DAYS = 31;

    @ManyToOne
    @JoinColumn(name = "order_product_id", nullable = false)
    private OrderProduct orderProduct;

    @Column(name = "order_product_filename", nullable = false)
    private String orderProductFilename;

    @Column(name = "download_maxdays", nullable = false)
    private Integer maxdays = DEFAULT_DOWNLOAD_MAX_DAYS;

    @Column(name = "download_count", nullable = false)
    private Integer downloadCount;

    public OrderProductDownload() {
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public String getOrderProductFilename() {
        return orderProductFilename;
    }

    public void setOrderProductFilename(String orderProductFilename) {
        this.orderProductFilename = orderProductFilename;
    }

    public Integer getMaxdays() {
        return maxdays;
    }

    public void setMaxdays(Integer maxdays) {
        this.maxdays = maxdays;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }
}
