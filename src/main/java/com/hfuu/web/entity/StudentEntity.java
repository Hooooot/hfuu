package com.hfuu.web.entity;

import java.util.List;

public class StudentEntity {
    private int stuId;
    private String stuNum;
    private String stuName;
    private String stuPw;
    private String stuSex;
    private String stuPhone;
    private String stuAvatar;
    private ClazzEntity classNum;
    private List<SubmitEntity> submit;

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuPw() {
        return stuPw;
    }

    public void setStuPw(String stuPw) {
        this.stuPw = stuPw;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getStuAvatar() {
        return stuAvatar;
    }

    public void setStuAvatar(String stuAvatar) {
        this.stuAvatar = stuAvatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        if (stuId != that.stuId) return false;
        if (stuNum != null ? !stuNum.equals(that.stuNum) : that.stuNum != null) return false;
        if (stuName != null ? !stuName.equals(that.stuName) : that.stuName != null) return false;
        if (stuPw != null ? !stuPw.equals(that.stuPw) : that.stuPw != null) return false;
        if (stuSex != null ? !stuSex.equals(that.stuSex) : that.stuSex != null) return false;
        if (stuPhone != null ? !stuPhone.equals(that.stuPhone) : that.stuPhone != null) return false;
        if (stuAvatar != null ? !stuAvatar.equals(that.stuAvatar) : that.stuAvatar != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stuId;
        result = 31 * result + (stuNum != null ? stuNum.hashCode() : 0);
        result = 31 * result + (stuName != null ? stuName.hashCode() : 0);
        result = 31 * result + (stuPw != null ? stuPw.hashCode() : 0);
        result = 31 * result + (stuSex != null ? stuSex.hashCode() : 0);
        result = 31 * result + (stuPhone != null ? stuPhone.hashCode() : 0);
        result = 31 * result + (stuAvatar != null ? stuAvatar.hashCode() : 0);
        return result;
    }

    public ClazzEntity getClassNum() {
        return classNum;
    }

    public void setClassNum(ClazzEntity classNum) {
        this.classNum = classNum;
    }

    public List<SubmitEntity> getSubmit() {
        return submit;
    }

    public void setSubmit(List<SubmitEntity> submit) {
        this.submit = submit;
    }
}
