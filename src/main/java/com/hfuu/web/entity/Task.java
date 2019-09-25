package com.hfuu.web.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 任务类：教师选择课程，布置课程任务
 *  taskId:自增主键
 *  taskName:任务名称
 *  taskDesc:任务具体描述(description)
 *  cozNum:外键，指向课程代码，表明是哪门课的任务。一门课程可包括多个任务
 *  tcNum:外键，指向教师工号，表明布置任务的教师
        （tNum可以通过cozNum查询course表获得，方便起见单独设立字段）
 *  pubTime:布置任务的时间，默认为当前系统时间。数据类型Timestamp
 *  deadline:任务提交的截至时间，建议在教师未设置的情况下设置为七天后。数据类型Timestamp
 * */
@Entity
@Table(name = "task", schema = "hfuutest")
public class Task {
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

        Task that = (Task) o;

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

    @Override
    public String toString() {
        String task = "[#" + taskId + ": " + taskName + ", " + cozNum + ", " + tcNum +", " + taskDesc + "]";
        return task;
    }
}
