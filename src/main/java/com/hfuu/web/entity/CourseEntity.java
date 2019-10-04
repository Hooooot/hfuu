package com.hfuu.web.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * 课程类：由班级代码classNum和教师工号tcNum确定一门课程
 *  cozId:自增主键
 *  cozNum:课程代码，9位（如041320013离散结构，可以重复）
 *  cozName:课程名称
 *  classNum:外键，开设班级代码
 *  tcNum:外键，授课教师工号
 */
/**
 * @Description :
 * @date : 2019/9/26 0:39
 * @author : Ciel-08
 * 最后修改时间：
 * 最后修改人：
 */
@Entity
@Table(name = "course", schema = "hfuutest")
public class CourseEntity implements Serializable {
    private int cozId;
    private String cozNum;
    private String cozName;
    private TeacherEntity tcEntity;
    private ClassEntity classEntity;
    private Set<TaskEntity> tasksFromCoz;

    @Id
    @Column(name = "cozId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getCozId() {
        return cozId;
    }

    public void setCozId(int cozId) {
        this.cozId = cozId;
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
    @Column(name = "cozName", nullable = true, length = 64)
    public String getCozName() {
        return cozName;
    }

    public void setCozName(String cozName) {
        this.cozName = cozName;
    }

    @ManyToOne
    @JoinColumn(name = "tcNum", referencedColumnName = "tcNum")
    public TeacherEntity getTcEntity() {
        return tcEntity;
    }

    public void setTcEntity(TeacherEntity tcEntity) {
        this.tcEntity = tcEntity;
    }

    @ManyToOne
    @JoinColumn(name = "classNum", referencedColumnName = "classNum")
    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    @OneToMany(mappedBy = "cozEntity")
    public Set<TaskEntity> getTasksFromCoz() {
        return tasksFromCoz;
    }

    public void setTasksFromCoz(Set<TaskEntity> tasksFromCoz) {
        this.tasksFromCoz = tasksFromCoz;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {

            return true;
        }
        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        CourseEntity that = (CourseEntity) o;

        if (cozId != that.cozId) {

            return false;
        }
        if (!Objects.equals(cozNum, that.cozNum)) {

            return false;
        }
        return Objects.equals(cozName, that.cozName);
    }

    @Override
    public int hashCode() {
        int result = cozId;
        result = 31 * result + (cozNum != null ? cozNum.hashCode() : 0);
        result = 31 * result + (cozName != null ? cozName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String course = "[#" + cozId + ": " + cozNum + ", " + cozName + ", " + classEntity.getClassName() + ", " + tcEntity.getTcName() + "]";
        return course;
    }
}
