package com.hfuu.web.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * 学生类：班级包括学生
 *  stuId:自增主键
 *  stuNum:学号，10位
 *  stuName:学生姓名
 *  stuPw:学生登录密码
 *  classNum:外键，指向班级代码
 *  stuSex:学生性别，男或女
 *  stuPhone:学生联系方式，11位
 *  stuAvatar:学生头像，目前设为varchar(64)保存图片名称
 */
/**
 * @Decription :
 * @CreateDate : 2019/9/26 0:39
 * @author : Ciel-08
 * 最后修改时间：
 * 最后修改人：
 */
@Entity
@Table(name = "student", schema = "hfuutest")
public class StudentEntity implements Serializable {
    private int stuId;
    private String stuNum;
    private String stuName;
    private String stuPw;
    private String stuSex;
    private String stuPhone;
    private String stuAvatar;
    private ClassEntity classEntity;
    private Set<SubmitEntity> submitsFromStu;

    @Id
    @Column(name = "stuId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    @Basic
    @Column(name = "stuNum", nullable = false, length = 10)
    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    @Basic
    @Column(name = "stuName", nullable = false, length = 8)
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Basic
    @Column(name = "stuPw", nullable = false, length = 20)
    public String getStuPw() {
        return stuPw;
    }

    public void setStuPw(String stuPw) {
        this.stuPw = stuPw;
    }

    @Basic
    @Column(name = "stuSex", nullable = true, length = 2)
    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    @Basic
    @Column(name = "stuPhone", nullable = true, length = 11)
    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    @Basic
    @Column(name = "stuAvatar", nullable = true, length = 64)
    public String getStuAvatar() {
        return stuAvatar;
    }

    public void setStuAvatar(String stuAvatar) {
        this.stuAvatar = stuAvatar;
    }

    @ManyToOne
    @JoinColumn(name = "classNum", referencedColumnName = "classNum")
    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    @OneToMany(mappedBy = "stuEntity")
    public Set<SubmitEntity> getSubmitsFromStu() {
        return submitsFromStu;
    }

    public void setSubmitsFromStu(Set<SubmitEntity> submitsFromStu) {
        this.submitsFromStu = submitsFromStu;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {

            return true;
        }
        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        StudentEntity that = (StudentEntity) o;

        if (stuId != that.stuId) {

            return false;
        }
        if (!Objects.equals(stuNum, that.stuNum)) {

            return false;
        }
        if (!Objects.equals(stuName, that.stuName)) {

            return false;
        }
        if (!Objects.equals(stuPw, that.stuPw)) {

            return false;
        }
        if (!Objects.equals(stuSex, that.stuSex)) {

            return false;
        }
        if (!Objects.equals(stuPhone, that.stuPhone)) {

            return false;
        }
        return Objects.equals(stuAvatar, that.stuAvatar);
    }

    @Override
    public int hashCode() {
        int result = stuId;
        result = 31 * result + (stuNum != null ? stuNum.hashCode() : 0);
        result = 31 * result + (stuName != null ? stuName.hashCode() : 0);
        result = 31 * result + (stuPw != null ? stuPw.hashCode() : 0);
        result = 31 * result + (stuSex != null ? stuSex.hashCode() : 0);
        result = 31 * result + (stuPhone != null ? stuPhone.hashCode() : 0);
        result = 31 * result + (stuAvatar != null ? stuAvatar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String student = "[#" + stuId + ": " + stuNum + ", " + stuName + ", " + stuSex +", " + classEntity.getClassName() + "]";
        return student;
    }
}
