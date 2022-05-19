package com.banner_management.backend.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "clicks")
public class ClickEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "banner_id", nullable = false)
    @NotNull(message = "Thiếu mã banner")
    private Integer bannerId;

    @Column(name = "section_id", nullable = false)
    @NotNull(message = "Thiếu mã khu vực")
    private Integer sectionId;

    @Column(name = "browser_name")
    @NotEmpty(message = "Thiếu tên trình duyệt")
    private String browserName;

    @Column(name = "status")
    private short status;

    @Column(name = "time_click")
    @NotNull
    private Timestamp timeClick;

    @Column(name = "user_click")
    @NotEmpty(message = "Thiếu tên người click")
    private String userClick;

    public ClickEntity() {
    }

    @Override
    public String toString() {
        return "ClickEntity{" +
                "ID=" + ID +
                ", bannerId=" + bannerId +
                ", sectionId=" + sectionId +
                ", browserName='" + browserName + '\'' +
                ", status=" + status +
                ", timeClick=" + timeClick +
                ", userClick='" + userClick + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Timestamp getTimeClick() {
        return timeClick;
    }

    public void setTimeClick(Timestamp timeClick) {
        this.timeClick = timeClick;
    }

    public String getUserClick() {
        return userClick;
    }

    public void setUserClick(String userClick) {
        this.userClick = userClick;
    }
}
