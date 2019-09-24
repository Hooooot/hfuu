package com.hfuu.web.entity;

import java.util.List;

public class DepartmentEntity {
    private int depId;
    private String depNum;
    private String depName;
    private List<ClazzEntity> clazz;
    private List<TeacherEntity> teacher;

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getDepNum() {
        return depNum;
    }

    public void setDepNum(String depNum) {
        this.depNum = depNum;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentEntity that = (DepartmentEntity) o;

        if (depId != that.depId) return false;
        if (depNum != null ? !depNum.equals(that.depNum) : that.depNum != null) return false;
        if (depName != null ? !depName.equals(that.depName) : that.depName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = depId;
        result = 31 * result + (depNum != null ? depNum.hashCode() : 0);
        result = 31 * result + (depName != null ? depName.hashCode() : 0);
        return result;
    }

    public List<ClazzEntity> getClazz() {
        return clazz;
    }

    public void setClazz(List<ClazzEntity> clazz) {
        this.clazz = clazz;
    }

    public List<TeacherEntity> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<TeacherEntity> teacher) {
        this.teacher = teacher;
    }
}
