package com.hfuu.web.entity;

import java.sql.Timestamp;

public class SubmitEntity {
    private int subId;
    private Timestamp subTime;
    private String subState;
    private String subFile;
    private StudentEntity stuNum;
    private TaskEntity taskId;

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public Timestamp getSubTime() {
        return subTime;
    }

    public void setSubTime(Timestamp subTime) {
        this.subTime = subTime;
    }

    public String getSubState() {
        return subState;
    }

    public void setSubState(String subState) {
        this.subState = subState;
    }

    public String getSubFile() {
        return subFile;
    }

    public void setSubFile(String subFile) {
        this.subFile = subFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubmitEntity that = (SubmitEntity) o;

        if (subId != that.subId) return false;
        if (subTime != null ? !subTime.equals(that.subTime) : that.subTime != null) return false;
        if (subState != null ? !subState.equals(that.subState) : that.subState != null) return false;
        if (subFile != null ? !subFile.equals(that.subFile) : that.subFile != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subId;
        result = 31 * result + (subTime != null ? subTime.hashCode() : 0);
        result = 31 * result + (subState != null ? subState.hashCode() : 0);
        result = 31 * result + (subFile != null ? subFile.hashCode() : 0);
        return result;
    }

    public StudentEntity getStuNum() {
        return stuNum;
    }

    public void setStuNum(StudentEntity stuNum) {
        this.stuNum = stuNum;
    }

    public TaskEntity getTaskId() {
        return taskId;
    }

    public void setTaskId(TaskEntity taskId) {
        this.taskId = taskId;
    }
}
