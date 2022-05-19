package com.banner_management.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sections")
public class SectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "page_id")
    @NotNull(message = "Thiếu mã trang web")
    private  int pageId;

    @Column(name = "div_id")
    @NotEmpty(message = "Thiếu mã khu vực")
    private String divId;

    @Column(name = "display_mode", columnDefinition = "0")
    private short status;

    public SectionEntity() {
    }

    @Override
    public String toString() {
        return "SectionEntity{" +
                "id=" + id +
                ", pageId=" + pageId +
                ", divId='" + divId + '\'' +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public String getDivId() {
        return divId;
    }

    public void setDivId(String divId) {
        this.divId = divId;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
