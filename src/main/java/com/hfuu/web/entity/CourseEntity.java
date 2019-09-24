package com.hfuu.web.entity;

import java.util.List;

public class CourseEntity {
    private int cozId;
    private String cozNum;
    private String cozName;
    private List<ClazzEntity> classNum;
    private List<TeacherEntity> tcNum;
    private List<TaskEntity> task;

    public int getCozId() {
        return cozId;
    }

    public void setCozId(int cozId) {
        this.cozId = cozId;
    }

    public String getCozNum() {
        return cozNum;
    }

    public void setCozNum(String cozNum) {
        this.cozNum = cozNum;
    }

    public String getCozName() {
        return cozName;
    }

    public void setCozName(String cozName) {
        this.cozName = cozName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (cozId != that.cozId) return false;
        if (cozNum != null ? !cozNum.equals(that.cozNum) : that.cozNum != null) return false;
        if (cozName != null ? !cozName.equals(that.cozName) : that.cozName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cozId;
        result = 31 * result + (cozNum != null ? cozNum.hashCode() : 0);
        result = 31 * result + (cozName != null ? cozName.hashCode() : 0);
        return result;
    }

    public List<ClazzEntity> getClassNum() {
        return classNum;
    }

    public void setClassNum(List<ClazzEntity> classNum) {
        this.classNum = classNum;
    }

    public List<TeacherEntity> getTcNum() {
        return tcNum;
    }

    public void setTcNum(List<TeacherEntity> tcNum) {
        this.tcNum = tcNum;
    }

    public List<TaskEntity> getTask() {
        return task;
    }

    public void setTask(List<TaskEntity> task) {
        this.task = task;
    }
}
