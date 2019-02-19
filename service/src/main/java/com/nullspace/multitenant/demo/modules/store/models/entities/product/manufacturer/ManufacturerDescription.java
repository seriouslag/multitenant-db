package com.nullspace.multitenant.demo.modules.store.models.entities.product.manufacturer;

import com.nullspace.multitenant.demo.modules.store.models.entities.common.Description;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "manufacturer_descriptions", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "manufacturer_id",
        })
})
public class ManufacturerDescription extends Description {
    @ManyToOne(targetEntity = Manufacturer.class)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @Column(name = "manufacturers_url")
    private String url;

    @Column(name = "url_clicked")
    private Integer urlClicked;

    @Column(name = "date_last_click")
    private Date dateLastClick;

    public ManufacturerDescription() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUrlClicked() {
        return urlClicked;
    }

    public void setUrlClicked(Integer urlClicked) {
        this.urlClicked = urlClicked;
    }

    public Date getDateLastClick() {
        return dateLastClick;
    }

    public void setDateLastClick(Date dateLastClick) {
        this.dateLastClick = dateLastClick;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
