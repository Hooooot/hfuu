package com.hfuu.web.entity;

import javax.persistence.*;

/**
 *  课程类
 * */
@Entity
@Table(name = "course", schema = "hfuutest")
public class CourseEntity {
    private int cozId;
    private String cozNum;

    @Id
    @Column(name = "cozId")
    public int getCozId() {
        return cozId;
    }

    public void setCozId(int cozId) {
        this.cozId = cozId;
    }

    @Basic
    @Column(name = "cozNum")
    public String getCozNum() {
        return cozNum;
    }

    public void setCozNum(String cozNum) {
        this.cozNum = cozNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (cozId != that.cozId) return false;
        return cozNum != null ? cozNum.equals(that.cozNum) : that.cozNum == null;
    }

    @Override
    public int hashCode() {
        int result = cozId;
        result = 31 * result + (cozNum != null ? cozNum.hashCode() : 0);
        return result;
    }
}
