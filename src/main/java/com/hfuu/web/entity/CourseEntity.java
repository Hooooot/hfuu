package com.hfuu.web.entity;

import javax.persistence.*;

/**
 * 课程类
 * */
@Entity
@Table(name = "course", schema = "hfuutest")
public class CourseEntity {
    private int cozId;
    private String cozNum;
    private String cozName;
    private String classNum;
    private String tcNum;

    @Id
    @Column(name = "cozId", nullable = false)
    public int getCozId() {
        return cozId;
    }

    public void setCozId(int cozId) {
        this.cozId = cozId;
    }

    @Basic
    @Column(name = "cozNum", nullable = true, length = 9)
    public String getCozNum() {
        return cozNum;
    }

    public void setCozNum(String cozNum) {
        this.cozNum = cozNum;
    }

    @Basic
    @Column(name = "cozName", nullable = true, length = 64)
    public String getCozName() {
        return cozName;
    }

    public void setCozName(String cozName) {
        this.cozName = cozName;
    }

    @Basic
    @Column(name = "classNum", nullable = true, length = 7)
    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    @Basic
    @Column(name = "tcNum", nullable = true, length = 10)
    public String getTcNum() {
        return tcNum;
    }

    public void setTcNum(String tcNum) {
        this.tcNum = tcNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (cozId != that.cozId) return false;
        if (cozNum != null ? !cozNum.equals(that.cozNum) : that.cozNum != null) return false;
        if (cozName != null ? !cozName.equals(that.cozName) : that.cozName != null) return false;
        if (classNum != null ? !classNum.equals(that.classNum) : that.classNum != null) return false;
        return tcNum != null ? tcNum.equals(that.tcNum) : that.tcNum == null;
    }

    @Override
    public int hashCode() {
        int result = cozId;
        result = 31 * result + (cozNum != null ? cozNum.hashCode() : 0);
        result = 31 * result + (cozName != null ? cozName.hashCode() : 0);
        result = 31 * result + (classNum != null ? classNum.hashCode() : 0);
        result = 31 * result + (tcNum != null ? tcNum.hashCode() : 0);
        return result;
    }
}
