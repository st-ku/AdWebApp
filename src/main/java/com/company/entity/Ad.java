package com.company.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Component
@Entity
@Table(name = "AD")
public class Ad {
    @Id
    @Column(name = "AD_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long adId;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "PUBLIC_PHONE_NUMBER")
    private String publicPhoneNumber;
    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "AVAILABLE", columnDefinition = "boolean default false", nullable = false)
    private Boolean available = false;
    @OneToOne
    private User user;
    @OneToOne
    private AdCategory adCategory;
    @Column(name = "AD_DAYS_ALIVE")
    private Integer adDaysAlive;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UploadFile> uploadFile;

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long age) {
        this.adId = age;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Ad() {

    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Set<UploadFile> getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(Set<UploadFile> uploadFile) {
        this.uploadFile = uploadFile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPublicPhoneNumber() {
        return publicPhoneNumber;
    }

    public void setPublicPhoneNumber(String publicPhoneNumber) {
        this.publicPhoneNumber = publicPhoneNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AdCategory getAdCategory() {
        return adCategory;
    }

    public void setAdCategory(AdCategory adCategory) {
        this.adCategory = adCategory;
    }

    public Integer getAdDaysAlive() {
        return adDaysAlive;
    }

    public void setAdDaysAlive(Integer adDaysAlive) {
        this.adDaysAlive = adDaysAlive;
    }

    public int getNumberOfFiles() {
        int i = 0;
        byte n;
        for (UploadFile upload : this.uploadFile) {
            if (!upload.getFileName().equals("")) {
                i++;
            }
        }
        return i;
    }
}
