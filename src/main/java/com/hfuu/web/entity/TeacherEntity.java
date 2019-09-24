package com.hfuu.web.entity;

import java.util.List;

public class TeacherEntity {
    private int tcId;
    private String tcNum;
    private String tcName;
    private String tcPw;
    private String tcSex;
    private String tcPhone;
    private String tcAvatar;
    private List<CourseEntity> course;
    private List<TaskEntity> task;
    private DepartmentEntity depNum;

    public int getTcId() {
        return tcId;
    }

    public void setTcId(int tcId) {
        this.tcId = tcId;
    }

    public String getTcNum() {
        return tcNum;
    }

    public void setTcNum(String tcNum) {
        this.tcNum = tcNum;
    }

    public String getTcName() {
        return tcName;
    }

    public void setTcName(String tcName) {
        this.tcName = tcName;
    }

    public String getTcPw() {
        return tcPw;
    }

    public void setTcPw(String tcPw) {
        this.tcPw = tcPw;
    }

    public String getTcSex() {
        return tcSex;
    }

    public void setTcSex(String tcSex) {
        this.tcSex = tcSex;
    }

    public String getTcPhone() {
        return tcPhone;
    }

    public void setTcPhone(String tcPhone) {
        this.tcPhone = tcPhone;
    }

    public String getTcAvatar() {
        return tcAvatar;
    }

    public void setTcAvatar(String tcAvatar) {
        this.tcAvatar = tcAvatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherEntity that = (TeacherEntity) o;

        if (tcId != that.tcId) return false;
        if (tcNum != null ? !tcNum.equals(that.tcNum) : that.tcNum != null) return false;
        if (tcName != null ? !tcName.equals(that.tcName) : that.tcName != null) return false;
        if (tcPw != null ? !tcPw.equals(that.tcPw) : that.tcPw != null) return false;
        if (tcSex != null ? !tcSex.equals(that.tcSex) : that.tcSex != null) return false;
        if (tcPhone != null ? !tcPhone.equals(that.tcPhone) : that.tcPhone != null) return false;
        if (tcAvatar != null ? !tcAvatar.equals(that.tcAvatar) : that.tcAvatar != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tcId;
        result = 31 * result + (tcNum != null ? tcNum.hashCode() : 0);
        result = 31 * result + (tcName != null ? tcName.hashCode() : 0);
        result = 31 * result + (tcPw != null ? tcPw.hashCode() : 0);
        result = 31 * result + (tcSex != null ? tcSex.hashCode() : 0);
        result = 31 * result + (tcPhone != null ? tcPhone.hashCode() : 0);
        result = 31 * result + (tcAvatar != null ? tcAvatar.hashCode() : 0);
        return result;
    }

    public List<CourseEntity> getCourse() {
        return course;
    }

    public void setCourse(List<CourseEntity> course) {
        this.course = course;
    }

    public List<TaskEntity> getTask() {
        return task;
    }

    public void setTask(List<TaskEntity> task) {
        this.task = task;
    }

    public DepartmentEntity getDepNum() {
        return depNum;
    }

    public void setDepNum(DepartmentEntity depNum) {
        this.depNum = depNum;
    }
}
