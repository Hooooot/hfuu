package com.hfuu.web.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * 院系类：
 *  depId:自增主键
 *  depNum:院系代码，2位（如计科系04）
 *  depName:院系名称：
 */
/**
 * @Decription :
 * @CreateDate : 2019/9/26 0:39
 * @author : Ciel-08
 * 最后修改时间：
 * 最后修改人：
 */
@Entity
@Table(name = "department", schema = "hfuutest")
public class DepEntity implements Serializable {
    private int depId;
    private String depNum;
    private String depName;
    private Set<ClassEntity> clazzsFromDep;
    private Set<TeacherEntity> tcsFromDep;

    @Id
    @Column(name = "depId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    @Basic
    @Column(name = "depNum", nullable = true, length = 2)
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

    @OneToMany(mappedBy = "depEntity")
    public Set<ClassEntity> getClazzsFromDep() {
        return clazzsFromDep;
    }

    public void setClazzsFromDep(Set<ClassEntity> clazzsFromDep) {
        this.clazzsFromDep = clazzsFromDep;
    }

    @OneToMany(mappedBy = "depEntity")
    public Set<TeacherEntity> getTcsFromDep() {
        return tcsFromDep;
    }

    public void setTcsFromDep(Set<TeacherEntity> tcsFromDep) {
        this.tcsFromDep = tcsFromDep;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {

            return true;
        }
        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        DepEntity that = (DepEntity) o;

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
