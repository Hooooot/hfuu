package com.hfuu.web.entity;

import javax.persistence.*;

/**
 * 院系类
 * */
@Entity
@Table(name = "department", schema = "hfuutest")
public class DepartmentEntity {
    private int depId;
    private String depNum;
    private String depName;

    @Id
    @Column(name = "depId", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentEntity that = (DepartmentEntity) o;

        if (depId != that.depId) return false;
        if (depNum != null ? !depNum.equals(that.depNum) : that.depNum != null) return false;
        return depName != null ? depName.equals(that.depName) : that.depName == null;
    }

    @Override
    public int hashCode() {
        int result = depId;
        result = 31 * result + (depNum != null ? depNum.hashCode() : 0);
        result = 31 * result + (depName != null ? depName.hashCode() : 0);
        return result;
    }
}
