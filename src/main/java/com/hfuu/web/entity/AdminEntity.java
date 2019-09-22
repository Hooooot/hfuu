package com.hfuu.web.entity;

import javax.persistence.*;

/**
 * 管理员类
 * */
@Entity
@Table(name = "admin", schema = "hfuutest")
public class AdminEntity {
    private int adminId;
    private String adminNum;
    private String adminName;
    private String adminPw;
    private String adminSex;
    private String adminPhone;
    private String adminAvatar;

    @Id
    @Column(name = "adminId", nullable = false)
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "adminNum", nullable = false, length = 10)
    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum;
    }

    @Basic
    @Column(name = "adminName", nullable = false, length = 8)
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Basic
    @Column(name = "adminPw", nullable = false, length = 20)
    public String getAdminPw() {
        return adminPw;
    }

    public void setAdminPw(String adminPw) {
        this.adminPw = adminPw;
    }

    @Basic
    @Column(name = "adminSex", nullable = true, length = 2)
    public String getAdminSex() {
        return adminSex;
    }

    public void setAdminSex(String adminSex) {
        this.adminSex = adminSex;
    }

    @Basic
    @Column(name = "adminPhone", nullable = true, length = 11)
    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    @Basic
    @Column(name = "adminAvatar", nullable = true, length = 64)
    public String getAdminAvatar() {
        return adminAvatar;
    }

    public void setAdminAvatar(String adminAvatar) {
        this.adminAvatar = adminAvatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminEntity that = (AdminEntity) o;

        if (adminId != that.adminId) return false;
        if (adminNum != null ? !adminNum.equals(that.adminNum) : that.adminNum != null) return false;
        if (adminName != null ? !adminName.equals(that.adminName) : that.adminName != null) return false;
        if (adminPw != null ? !adminPw.equals(that.adminPw) : that.adminPw != null) return false;
        if (adminSex != null ? !adminSex.equals(that.adminSex) : that.adminSex != null) return false;
        if (adminPhone != null ? !adminPhone.equals(that.adminPhone) : that.adminPhone != null) return false;
        return adminAvatar != null ? adminAvatar.equals(that.adminAvatar) : that.adminAvatar == null;
    }

    @Override
    public int hashCode() {
        int result = adminId;
        result = 31 * result + (adminNum != null ? adminNum.hashCode() : 0);
        result = 31 * result + (adminName != null ? adminName.hashCode() : 0);
        result = 31 * result + (adminPw != null ? adminPw.hashCode() : 0);
        result = 31 * result + (adminSex != null ? adminSex.hashCode() : 0);
        result = 31 * result + (adminPhone != null ? adminPhone.hashCode() : 0);
        result = 31 * result + (adminAvatar != null ? adminAvatar.hashCode() : 0);
        return result;
    }
}
