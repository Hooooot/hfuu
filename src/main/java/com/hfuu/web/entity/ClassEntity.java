package com.hfuu.web.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * 班级类：院系下设班级
 *  classId:自增主键
 *  classNum:班级代码，7位（如17软二1706072）
 *  className:班级名称
 *  depNum:外键，指向院系代码。删除院系前需先修改下设班级
 *
 * @author: Ciel-08
 * 创建时间：2019/9/26 0:39
 * 最后修改时间：
 * 最后修改人：
 */
@Entity
@Table(name = "clazz", schema = "hfuutest")
public class ClassEntity implements Serializable {
    private int classId;
    private String classNum;
    private String className;
    private DepEntity depEntity;
    private Set<CourseEntity> coursesFromClass;
    private Set<StudentEntity> stusFromClass;

    @Id
    @Column(name = "classId", nullable = false)
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "classNum", nullable = true, length = 7, unique = true)
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

    @ManyToOne
    @JoinColumn(name = "depNum", referencedColumnName = "depNum")
    public DepEntity getDepEntity() {
        return depEntity;
    }

    public void setDepEntity(DepEntity depEntity) {
        this.depEntity = depEntity;
    }

    @OneToMany(mappedBy = "classEntity")
    public Set<CourseEntity> getCoursesFromClass() {
        return coursesFromClass;
    }

    public void setCoursesFromClass(Set<CourseEntity> coursesFromClass) {
        this.coursesFromClass = coursesFromClass;
    }

    @OneToMany(mappedBy = "classEntity")
    public Set<StudentEntity> getStusFromClass() {
        return stusFromClass;
    }

    public void setStusFromClass(Set<StudentEntity> stusFromClass) {
        this.stusFromClass = stusFromClass;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {

            return true;
        }
        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        ClassEntity that = (ClassEntity) o;

        if (classId != that.classId) {

            return false;
        }
        if (!Objects.equals(classNum, that.classNum)) {

            return false;
        }
        return Objects.equals(className, that.className);
    }

    @Override
    public int hashCode() {
        int result = classId;
        result = 31 * result + (classNum != null ? classNum.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String clazz = "[#" + classId + ": " + classNum + ", " + className + ", " + depEntity.getDepName() + "]";
        return clazz;
    }
}
