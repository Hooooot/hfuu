package com.hfuu.web.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 任务类：教师选择课程，布置课程任务
 *  taskId:自增主键
 *  taskName:任务名称
 *  taskDesc:任务具体描述(description)
 *  cozId:外键，指向课程id，表明是哪门课的任务。一门课程可包括多个任务
 *  tcNum:外键，指向教师工号，表明布置任务的教师
        （tNum可以通过cozId查询course表获得，方便起见单独设立字段）
 *  pubTime:布置任务的时间，默认为当前系统时间。数据类型Timestamp
 *  deadline:任务提交的截至时间，建议在教师未设置的情况下设置为七天后。数据类型Timestamp
 */
/**
 * @Description :
 * @date : 2019/9/26 0:39
 * @author : Ciel-08
 * 最后修改时间：2019/10/04 13:38
 * 最后修改人：Ciel-08
 */
@Entity
@Table(name = "task", schema = "hfuutest")
public class TaskEntity implements Serializable {
    private int taskId;
    private String taskName;
    private String taskDesc;
    private Timestamp pubTime;
    private Timestamp deadline;
    private CourseEntity cozEntity;
    private TeacherEntity tcEntity;
    private Set<SubmitEntity> submitsFromTask;

    @Id
    @Column(name = "taskId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "pubTime", nullable = false)
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

    @ManyToOne
    @JoinColumn(name = "cozId", referencedColumnName = "cozId")
    public CourseEntity getCozEntity() {
        return cozEntity;
    }

    public void setCozEntity(CourseEntity cozEntity) {
        this.cozEntity = cozEntity;
    }

    @ManyToOne
    @JoinColumn(name = "tcNum", referencedColumnName = "tcNum")
    public TeacherEntity getTcEntity() {
        return tcEntity;
    }

    public void setTcEntity(TeacherEntity tcEntity) {
        this.tcEntity = tcEntity;
    }

    @OneToMany(mappedBy = "taskEntity")
    public Set<SubmitEntity> getSubmitsFromTask() {
        return submitsFromTask;
    }

    public void setSubmitsFromTask(Set<SubmitEntity> submitsFromTask) {
        this.submitsFromTask = submitsFromTask;
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
        result = 31 * result + (pubTime != null ? pubTime.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "[#" + taskId + ": " + taskName + ", " + cozEntity.getCozName() + ", " + tcEntity.getTcName() +", " + taskDesc + "]";
    }

    public Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>(8);
        map.put("taskId", taskId);
        map.put("taskName", taskName);
        map.put("taskDesc", taskDesc);
        map.put("pubTime", pubTime);
        map.put("deadline", deadline);
        map.put("cozName", cozEntity.getCozName());
        map.put("tcName", tcEntity.getTcName());
        map.put("submitSet", submitsFromTask);
        return map;
    }

}
