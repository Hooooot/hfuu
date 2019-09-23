package com.hfuu.web.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * 管理员类：使用工号和密码登录
 *  adminId:自增主键
 *  adminNum:管理员工号
 *  adminName:管理员姓名
 *  adminPw:管理员登录密码
 *  adminSex:管理员性别，男或女
 *  adminPhone:管理员联系方式，11位
 *  adminAvatar:管理员头像，目前设为varchar(64)保存图片名称
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
    @Column(name = "adminNum", nullable = false, unique = true, length = 10)
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
        if (this == o) {

            return true;
        }
        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        AdminEntity that = (AdminEntity) o;

        if (adminId != that.adminId) {

            return false;
        }
        if (!Objects.equals(adminNum, that.adminNum)) {

            return false;
        }
        if (!Objects.equals(adminName, that.adminName)) {

            return false;
        }
        if (!Objects.equals(adminPw, that.adminPw)) {

            return false;
        }
        if (!Objects.equals(adminSex, that.adminSex)) {

            return false;
        }
        if (!Objects.equals(adminPhone, that.adminPhone)) {

            return false;
        }
        return Objects.equals(adminAvatar, that.adminAvatar);
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
