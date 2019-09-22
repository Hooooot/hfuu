package com.hfuu.web.entity;

import javax.persistence.*;

/**
 * 管理员表
 * */
@Entity
@Table(name = "admin", schema = "hfuutest")
public class AdminEntity {
    private int adminId;
    private String adminNum;
    private String adminPw;

    @Id
    @Column(name = "adminId")
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "adminNum")
    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum;
    }

    @Basic
    @Column(name = "adminPw")
    public String getAdminPw() {
        return adminPw;
    }

    public void setAdminPw(String adminPw) {
        this.adminPw = adminPw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminEntity that = (AdminEntity) o;

        if (adminId != that.adminId) return false;
        if (adminNum != null ? !adminNum.equals(that.adminNum) : that.adminNum != null) return false;
        return adminPw != null ? adminPw.equals(that.adminPw) : that.adminPw == null;
    }

    @Override
    public int hashCode() {
        int result = adminId;
        result = 31 * result + (adminNum != null ? adminNum.hashCode() : 0);
        result = 31 * result + (adminPw != null ? adminPw.hashCode() : 0);
        return result;
    }
}
