package com.hfuu.web.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * 课程类：由班级代码classNum和教师工号tcNum确定一门课程
 *  cozId:自增主键
 *  cozNum:课程代码，9位（如041320013离散结构，可以重复）
 *  cozName:课程名称
 *  classNum:外键，开设班级代码
 *  tcNum:外键，授课教师工号
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        if (this == o) {

            return true;
        }
        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        CourseEntity that = (CourseEntity) o;

        if (cozId != that.cozId) {

            return false;
        }
        if (!Objects.equals(cozNum, that.cozNum)) {

            return false;
        }
        if (!Objects.equals(cozName, that.cozName)) {

            return false;
        }
        if (!Objects.equals(classNum, that.classNum)) {

            return false;
        }
        return Objects.equals(tcNum, that.tcNum);
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

    @Override
    public String toString() {
        String course = "[#" + cozId + ": " + cozNum + ", " + cozName + ", " + classNum + ", " + tcNum + "]";
        return course;
    }
}
