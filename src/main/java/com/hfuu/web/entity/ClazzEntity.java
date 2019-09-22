package com.hfuu.web.entity;

import javax.persistence.*;

/**
 *  班级类
 * */
@Entity
@Table(name = "clazz", schema = "hfuutest")
public class ClazzEntity {
    private int classId;
    private String classNum;
    private String className;

    @Id
    @Column(name = "classId")
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "classNum")
    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    @Basic
    @Column(name = "className")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClazzEntity that = (ClazzEntity) o;

        if (classId != that.classId) return false;
        if (classNum != null ? !classNum.equals(that.classNum) : that.classNum != null) return false;
        return className != null ? className.equals(that.className) : that.className == null;
    }

    @Override
    public int hashCode() {
        int result = classId;
        result = 31 * result + (classNum != null ? classNum.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        return result;
    }
}
