package com.hfuu.web.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * 学生类
 * */
@Entity
@Table(name = "student", schema = "hfuutest")
public class StudentEntity {
    private int stuId;
    private String stuNum;
    private String stuName;
    private String stuPw;
    private String classNum;
    private String stuSex;
    private String stuPhone;
    private String stuAvatar;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stuId", nullable = false)
    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    @Basic
    @Column(name = "stuNum", nullable = false, unique = true, length = 10)
    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    @Basic
    @Column(name = "stuName", nullable = false, length = 8)
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Basic
    @Column(name = "stuPw", nullable = false, length = 20)
    public String getStuPw() {
        return stuPw;
    }

    public void setStuPw(String stuPw) {
        this.stuPw = stuPw;
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
    @Column(name = "stuSex", nullable = true, length = 2)
    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    @Basic
    @Column(name = "stuPhone", nullable = true, length = 11)
    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    @Basic
    @Column(name = "stuAvatar", nullable = true, length = 64)
    public String getStuAvatar() {
        return stuAvatar;
    }

    public void setStuAvatar(String stuAvatar) {
        this.stuAvatar = stuAvatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {

            return true;
        }
        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        StudentEntity that = (StudentEntity) o;

        if (stuId != that.stuId) {

            return false;
        }
        if (!Objects.equals(stuNum, that.stuNum)) {

            return false;
        }
        if (!Objects.equals(stuName, that.stuName)) {

            return false;
        }
        if (!Objects.equals(stuPw, that.stuPw)) {

            return false;
        }
        if (!Objects.equals(classNum, that.classNum)) {

            return false;
        }
        if (!Objects.equals(stuSex, that.stuSex)) {

            return false;
        }
        if (!Objects.equals(stuPhone, that.stuPhone)) {

            return false;
        }
        return Objects.equals(stuAvatar, that.stuAvatar);
    }

    @Override
    public int hashCode() {
        int result = stuId;
        result = 31 * result + (stuNum != null ? stuNum.hashCode() : 0);
        result = 31 * result + (stuName != null ? stuName.hashCode() : 0);
        result = 31 * result + (stuPw != null ? stuPw.hashCode() : 0);
        result = 31 * result + (classNum != null ? classNum.hashCode() : 0);
        result = 31 * result + (stuSex != null ? stuSex.hashCode() : 0);
        result = 31 * result + (stuPhone != null ? stuPhone.hashCode() : 0);
        result = 31 * result + (stuAvatar != null ? stuAvatar.hashCode() : 0);
        return result;
    }
}
