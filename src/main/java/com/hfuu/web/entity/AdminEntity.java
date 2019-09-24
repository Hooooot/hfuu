package com.hfuu.web.entity;

public class AdminEntity {
    private int adminId;
    private String adminNum;
    private String adminName;
    private String adminPw;
    private String adminSex;
    private String adminPhone;
    private String adminAvatar;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPw() {
        return adminPw;
    }

    public void setAdminPw(String adminPw) {
        this.adminPw = adminPw;
    }

    public String getAdminSex() {
        return adminSex;
    }

    public void setAdminSex(String adminSex) {
        this.adminSex = adminSex;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

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
        if (adminAvatar != null ? !adminAvatar.equals(that.adminAvatar) : that.adminAvatar != null) return false;

        return true;
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
