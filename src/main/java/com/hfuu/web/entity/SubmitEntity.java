package com.hfuu.web.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 提交的报告类
 * */
@Entity
@Table(name = "submit", schema = "hfuutest")
public class SubmitEntity {
    private int subId;
    private Timestamp subTime;
    private String subState;
    private String subFile;

    @Id
    @Column(name = "subId")
    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    @Basic
    @Column(name = "subTime")
    public Timestamp getSubTime() {
        return subTime;
    }

    public void setSubTime(Timestamp subTime) {
        this.subTime = subTime;
    }

    @Basic
    @Column(name = "subState")
    public String getSubState() {
        return subState;
    }

    public void setSubState(String subState) {
        this.subState = subState;
    }

    @Basic
    @Column(name = "subFile")
    public String getSubFile() {
        return subFile;
    }

    public void setSubFile(String subFile) {
        this.subFile = subFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubmitEntity that = (SubmitEntity) o;

        if (subId != that.subId) return false;
        if (subTime != null ? !subTime.equals(that.subTime) : that.subTime != null) return false;
        if (subState != null ? !subState.equals(that.subState) : that.subState != null) return false;
        return subFile != null ? subFile.equals(that.subFile) : that.subFile == null;
    }

    @Override
    public int hashCode() {
        int result = subId;
        result = 31 * result + (subTime != null ? subTime.hashCode() : 0);
        result = 31 * result + (subState != null ? subState.hashCode() : 0);
        result = 31 * result + (subFile != null ? subFile.hashCode() : 0);
        return result;
    }
}
