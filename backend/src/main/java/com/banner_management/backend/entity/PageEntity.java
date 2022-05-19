package com.banner_management.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pages")
public class PageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "website_id")
    @NotNull(message = "Thiếu mã trang web")
    private int websiteId;

    @Column(name = "page_name")
    @NotEmpty(message = "Thiếu tên trang web")
    private String pageName;

    @Column(name = "page_url")
    @NotEmpty(message = "Thiếu url trang web")
    private String pageUrl;

    public PageEntity() {
    }

    @Override
    public String toString() {
        return "PageEntity{" +
                "id=" + id +
                ", websiteId=" + websiteId +
                ", pageName='" + pageName + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(int websiteId) {
        this.websiteId = websiteId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
}
