package com.hfuu.web.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 作业任务类
 * */
@Entity
@Table(name = "task", schema = "hfuutest")
public class TaskEntity {
    private int taskId;
    private String taskName;
    private String taskDesc;
    private Timestamp pubTime;
    private Timestamp deadline;

    @Id
    @Column(name = "taskId")
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "taskName")
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Basic
    @Column(name = "taskDesc")
    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    @Basic
    @Column(name = "pubTime")
    public Timestamp getPubTime() {
        return pubTime;
    }

    public void setPubTime(Timestamp pubTime) {
        this.pubTime = pubTime;
    }

    @Basic
    @Column(name = "deadline")
    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskEntity that = (TaskEntity) o;

        if (taskId != that.taskId) return false;
        if (taskName != null ? !taskName.equals(that.taskName) : that.taskName != null) return false;
        if (taskDesc != null ? !taskDesc.equals(that.taskDesc) : that.taskDesc != null) return false;
        if (pubTime != null ? !pubTime.equals(that.pubTime) : that.pubTime != null) return false;
        return deadline != null ? deadline.equals(that.deadline) : that.deadline == null;
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + (taskDesc != null ? taskDesc.hashCode() : 0);
        result = 31 * result + (pubTime != null ? pubTime.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        return result;
    }
}
