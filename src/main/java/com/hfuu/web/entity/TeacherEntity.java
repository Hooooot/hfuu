package com.hfuu.web.entity;

import javax.persistence.*;

/**
 * 教师类
 * */
@Entity
@Table(name = "teacher", schema = "hfuutest")
public class TeacherEntity {
    private int tcId;
    private String tcNum;
    private String tcName;
    private String tcPw;
    private String depNum;
    private String tcSex;
    private String tcPhone;
    private String tcAvatar;

    @Id
    @Column(name = "tcId", nullable = false)
    public int getTcId() {
        return tcId;
    }

    public void setTcId(int tcId) {
        this.tcId = tcId;
    }

    @Basic
    @Column(name = "tcNum", nullable = false, length = 10)
    public String getTcNum() {
        return tcNum;
    }

    public void setTcNum(String tcNum) {
        this.tcNum = tcNum;
    }

    @Basic
    @Column(name = "tcName", nullable = false, length = 8)
    public String getTcName() {
        return tcName;
    }

    public void setTcName(String tcName) {
        this.tcName = tcName;
    }

    @Basic
    @Column(name = "tcPw", nullable = false, length = 20)
    public String getTcPw() {
        return tcPw;
    }

    public void setTcPw(String tcPw) {
        this.tcPw = tcPw;
    }

    @Basic
    @Column(name = "depNum", nullable = true, length = 2)
    public String getDepNum() {
        return depNum;
    }

    public void setDepNum(String depNum) {
        this.depNum = depNum;
    }

    @Basic
    @Column(name = "tcSex", nullable = true, length = 2)
    public String getTcSex() {
        return tcSex;
    }

    public void setTcSex(String tcSex) {
        this.tcSex = tcSex;
    }

    @Basic
    @Column(name = "tcPhone", nullable = true, length = 11)
    public String getTcPhone() {
        return tcPhone;
    }

    public void setTcPhone(String tcPhone) {
        this.tcPhone = tcPhone;
    }

    @Basic
    @Column(name = "tcAvatar", nullable = true, length = 64)
    public String getTcAvatar() {
        return tcAvatar;
    }

    public void setTcAvatar(String tcAvatar) {
        this.tcAvatar = tcAvatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherEntity that = (TeacherEntity) o;

        if (tcId != that.tcId) return false;
        if (tcNum != null ? !tcNum.equals(that.tcNum) : that.tcNum != null) return false;
        if (tcName != null ? !tcName.equals(that.tcName) : that.tcName != null) return false;
        if (tcPw != null ? !tcPw.equals(that.tcPw) : that.tcPw != null) return false;
        if (depNum != null ? !depNum.equals(that.depNum) : that.depNum != null) return false;
        if (tcSex != null ? !tcSex.equals(that.tcSex) : that.tcSex != null) return false;
        if (tcPhone != null ? !tcPhone.equals(that.tcPhone) : that.tcPhone != null) return false;
        return tcAvatar != null ? tcAvatar.equals(that.tcAvatar) : that.tcAvatar == null;
    }

    @Override
    public int hashCode() {
        int result = tcId;
        result = 31 * result + (tcNum != null ? tcNum.hashCode() : 0);
        result = 31 * result + (tcName != null ? tcName.hashCode() : 0);
        result = 31 * result + (tcPw != null ? tcPw.hashCode() : 0);
        result = 31 * result + (depNum != null ? depNum.hashCode() : 0);
        result = 31 * result + (tcSex != null ? tcSex.hashCode() : 0);
        result = 31 * result + (tcPhone != null ? tcPhone.hashCode() : 0);
        result = 31 * result + (tcAvatar != null ? tcAvatar.hashCode() : 0);
        return result;
    }
}
