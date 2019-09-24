package com.hfuu.web.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * 班级类：院系下设班级
 *  classId:自增主键
 *  classNum:班级代码，7位（如17软二1706072）
 *  className:班级名称
 *  depNum:外键，指向院系代码。删除院系前需先修改下设班级
 * */
@Entity
@Table(name = "clazz", schema = "hfuutest")
public class ClazzEntity {
    private int classId;
    private String classNum;
    private String className;
    private String depNum;

    @Id
    @Column(name = "classId", nullable = false)
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "classNum", nullable = true, unique = true, length = 7)
    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    @Basic
    @Column(name = "className", nullable = true, length = 64)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Basic
    @Column(name = "depNum", nullable = true, length = 2)
    public String getDepNum() {
        return depNum;
    }

    public void setDepNum(String depNum) {
        this.depNum = depNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {

            return true;
        }
        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        ClazzEntity that = (ClazzEntity) o;

        if (classId != that.classId) {

            return false;
        }
        if (!Objects.equals(classNum, that.classNum)) {

            return false;
        }
        if (!Objects.equals(className, that.className)) {
            return false;
        }
        return Objects.equals(depNum, that.depNum);
    }

    @Override
    public int hashCode() {
        int result = classId;
        result = 31 * result + (classNum != null ? classNum.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (depNum != null ? depNum.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String clazz = "[#" + classId + ": " + classNum + ", " + className + ", " + depNum + "]";
        return clazz;
    }
}
