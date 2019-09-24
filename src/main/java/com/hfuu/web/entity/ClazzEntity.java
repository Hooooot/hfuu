package com.hfuu.web.entity;

import java.util.List;

public class ClazzEntity {
    private int classId;
    private String classNum;
    private String className;
    private DepartmentEntity depNum;
    private List<CourseEntity> course;
    private List<StudentEntity> student;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

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
        if (className != null ? !className.equals(that.className) : that.className != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classId;
        result = 31 * result + (classNum != null ? classNum.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        return result;
    }

    public DepartmentEntity getDepNum() {
        return depNum;
    }

    public void setDepNum(DepartmentEntity depNum) {
        this.depNum = depNum;
    }

    public List<CourseEntity> getCourse() {
        return course;
    }

    public void setCourse(List<CourseEntity> course) {
        this.course = course;
    }

    public List<StudentEntity> getStudent() {
        return student;
    }

    public void setStudent(List<StudentEntity> student) {
        this.student = student;
    }
}
