package com.hfuu.web.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 提交类：任务布置后，自动分发给开设班级下的学生。
 *    具体而言，每插入一条任务记录，就会为任务所属课程的这门课所在开设班级下的每位学生生成一条提交记录，提交状态为待提交。
 *  sbuId:自增主键
 *  taskId:外键，任务id，指出是哪个任务下的提交记录
 *  stuNum:外键，学生学号，指出提交人
 *  subTime:学生提交时间，默认为当前系统时间。数据类型Timestamp
 *  subState:提交状态，分为待提交、待批阅和已批阅。默认待提交
 *  subFile:提交的文件，类型暂时为varchar(64)
 */
/**
 * @Description :
 * @date : 2019/9/26 0:39
 * @author : Ciel-08
 * 最后修改时间：
 * 最后修改人：
 */
@Entity
@Table(name = "submit", schema = "hfuutest")
public class SubmitEntity implements Serializable {
    private int subId;
    private Timestamp subTime;
    private String subState;
    private String subFile;
    private Short score;
    private StudentEntity stuEntity;
    private TaskEntity taskEntity;

    @Id
    @Column(name = "subId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    @Basic
    @Column(name = "subTime", nullable = false)
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

    @Basic
    @Column(name = "score", nullable = true)
    public Short getScore() {
        return score;
    }

    public void setScore(Short score) {
        this.score = score;
    }

    @ManyToOne
    @JoinColumn(name = "stuNum", referencedColumnName = "stuNum")
    public StudentEntity getStuEntity() {
        return stuEntity;
    }

    public void setStuEntity(StudentEntity stuEntity) {
        this.stuEntity = stuEntity;
    }

    @ManyToOne
    @JoinColumn(name = "taskId", referencedColumnName = "taskId", nullable = false)
    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
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
        result = 31 * result + (subTime != null ? subTime.hashCode() : 0);
        result = 31 * result + (subState != null ? subState.hashCode() : 0);
        result = 31 * result + (subFile != null ? subFile.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String submit = "[#" + subId + ": " + taskEntity.getTaskId() + ", " + stuEntity.getStuNum() + ", " + subTime +", " + subState + ", " + score + "]";
        return submit;
    }
}
