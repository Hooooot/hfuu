package com.hfuu.web.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 学生提交的作业类
 * */
@Entity
@Table(name = "submit", schema = "hfuutest")
public class SubmitEntity {
    private int subId;
    private Integer taskId;
    private String stuNum;
    private Timestamp subTime;
    private String subState;
    private String subFile;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subId", nullable = false)
    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    @Basic
    @Column(name = "taskId", nullable = false)
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "stuNum", nullable = true, length = 10)
    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    @Basic
    @Column(name = "subTime", nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    public Timestamp getSubTime() {
        return subTime;
    }

    public void setSubTime(Timestamp subTime) {
        this.subTime = subTime;
    }

    @Basic
    @Column(name = "subState", nullable = true, length = 6)
    public String getSubState() {
        return subState;
    }

    public void setSubState(String subState) {
        this.subState = subState;
    }

    @Basic
    @Column(name = "subFile", nullable = true, length = 64)
    public String getSubFile() {
        return subFile;
    }

    public void setSubFile(String subFile) {
        this.subFile = subFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SubmitEntity that = (SubmitEntity) o;

        if (subId != that.subId) {
            return false;
        }
        if (!Objects.equals(taskId, that.taskId)) {
            return false;
        }
        if (!Objects.equals(stuNum, that.stuNum)) {
            return false;
        }
        if (!Objects.equals(subTime, that.subTime)) {
            return false;
        }
        if (!Objects.equals(subState, that.subState)) {
            return false;
        }
        return Objects.equals(subFile, that.subFile);
    }

    @Override
    public int hashCode() {
        int result = subId;
        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
        result = 31 * result + (stuNum != null ? stuNum.hashCode() : 0);
        result = 31 * result + (subTime != null ? subTime.hashCode() : 0);
        result = 31 * result + (subState != null ? subState.hashCode() : 0);
        result = 31 * result + (subFile != null ? subFile.hashCode() : 0);
        return result;
    }
}
