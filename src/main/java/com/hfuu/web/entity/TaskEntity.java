package com.hfuu.web.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 教师布置的作业类
 * */
@Entity
@Table(name = "task", schema = "hfuutest")
public class TaskEntity {
    private int taskId;
    private String taskName;
    private String taskDesc;
    private String cozNum;
    private String tcNum;
    private Timestamp pubTime;
    private Timestamp deadline;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskId", nullable = false)
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "taskName", nullable = true, length = 64)
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Basic
    @Column(name = "taskDesc", nullable = true, length = 128)
    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    @Basic
    @Column(name = "cozNum", nullable = true, length = 9)
    public String getCozNum() {
        return cozNum;
    }

    public void setCozNum(String cozNum) {
        this.cozNum = cozNum;
    }

    @Basic
    @Column(name = "tcNum", nullable = true, length = 10)
    public String getTcNum() {
        return tcNum;
    }

    public void setTcNum(String tcNum) {
        this.tcNum = tcNum;
    }

    @Basic
    @Column(name = "pubTime", nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    public Timestamp getPubTime() {
        return pubTime;
    }

    public void setPubTime(Timestamp pubTime) {
        this.pubTime = pubTime;
    }

    @Basic
    @Column(name = "deadline", nullable = true)
    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskEntity that = (TaskEntity) o;

        if (taskId != that.taskId) {
            return false;
        }
        if (!Objects.equals(taskName, that.taskName)) {
            return false;
        }
        if (!Objects.equals(taskDesc, that.taskDesc)) {
            return false;
        }
        if (!Objects.equals(cozNum, that.cozNum)) {
            return false;
        }
        if (!Objects.equals(tcNum, that.tcNum)) {
            return false;
        }
        if (!Objects.equals(pubTime, that.pubTime)) {
            return false;
        }
        return Objects.equals(deadline, that.deadline);
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + (taskDesc != null ? taskDesc.hashCode() : 0);
        result = 31 * result + (cozNum != null ? cozNum.hashCode() : 0);
        result = 31 * result + (tcNum != null ? tcNum.hashCode() : 0);
        result = 31 * result + (pubTime != null ? pubTime.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        return result;
    }
}
