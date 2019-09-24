package com.hfuu.web.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * 院系类：
 *  depId:自增主键
 *  depNum:院系代码，2位（如计科系04）
 *  depName:院系名称
 * */
@Entity
@Table(name = "department", schema = "hfuutest")
public class DepartmentEntity {
    private int depId;
    private String depNum;
    private String depName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "depId", nullable = false)
    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    @Basic
    @Column(name = "depNum", nullable = true, unique = true, length = 2)
    public String getDepNum() {
        return depNum;
    }

    public void setDepNum(String depNum) {
        this.depNum = depNum;
    }

    @Basic
    @Column(name = "depName", nullable = true, length = 64)
    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {

            return true;
        }
        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        DepartmentEntity that = (DepartmentEntity) o;

        if (depId != that.depId) {

            return false;
        }
        if (!Objects.equals(depNum, that.depNum)) {

            return false;
        }
        return Objects.equals(depName, that.depName);
    }

    @Override
    public int hashCode() {
        int result = depId;
        result = 31 * result + (depNum != null ? depNum.hashCode() : 0);
        result = 31 * result + (depName != null ? depName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String department = "[#" + depId + ": " + depNum + ", " + depName + "]";
        return department;
    }
}
