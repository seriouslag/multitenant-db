package com.nullspace.multitenant.modules.store.models.entities.order.filehistory;

import com.nullspace.multitenant.models.entities.BaseEntity;
import com.nullspace.multitenant.modules.store.models.entities.merchant.MerchantStore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="file_histories", uniqueConstraints={
        @UniqueConstraint(
                columnNames={
                        "merchant_id",
                        "file_id"
                }
        )
})
public class FileHistory extends BaseEntity {
    @ManyToOne(targetEntity = MerchantStore.class)
    @JoinColumn(name = "merchant_id", nullable = false)
    private MerchantStore store;

    @Column(name = "file_id")
    private Long fileId;

    @Column ( name="filesize", nullable=false )
    private Integer filesize;

    @Temporal(TemporalType.TIMESTAMP )
    @Column ( name="date_added", length=0, nullable=false )
    private Date dateAdded;

    @Temporal(TemporalType.TIMESTAMP)
    @Column ( name="date_deleted", length=0 )
    private Date dateDeleted;

    @Temporal(TemporalType.TIMESTAMP)
    @Column ( name="accounted_date", length=0 )
    private Date accountedDate;

    @Column ( name="download_count", nullable=false )
    private Integer downloadCount;

    public FileHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MerchantStore getStore() {
        return store;
    }

    public void setStore(MerchantStore store) {
        this.store = store;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public Date getDateAdded() {
        return (Date)dateAdded.clone();
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = (Date)dateAdded.clone();
    }

    public Date getDateDeleted() {
        return (Date)dateDeleted.clone();
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = (Date)dateDeleted.clone();
    }

    public Date getAccountedDate() {
        return (Date)accountedDate.clone();
    }

    public void setAccountedDate(Date accountedDate) {
        this.accountedDate = (Date)accountedDate.clone();
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }
}
