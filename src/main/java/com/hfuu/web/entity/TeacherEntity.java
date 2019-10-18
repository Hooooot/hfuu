package com.hfuu.web.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 教师类：院系包括教师
 *  tcId:自增主键
 *  tcNum:教师工号，10位
 *  tcName:教师姓名
 *  tcPw:教师登录密码
 *  depNum:外键，指向院系代码，删除院系前需先修改其下教师
 *  tcSex:教师性别，男或女
 *  tcPhone:教师联系方式，11位
 *  tcAvatar:教师头像，目前设为varchar(64)保存图片名称
 */
/**
 * @Description :
 * @date : 2019/9/26 0:39
 * @author : Ciel-08
 * 最后修改时间：
 * 最后修改人：
 */
@Entity
@Table(name = "teacher", schema = "hfuutest")
public class TeacherEntity implements Serializable {
    private int tcId;
    private String tcNum;
    private String tcName;
    private String tcPw;
    private String tcSex;
    private String tcPhone;
    private String tcAvatar;
    private Set<CourseEntity> cozsFromTc;
    private Set<TaskEntity> tasksFromTc;
    private DepEntity depEntity;

    @Id
    @Column(name = "tcId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getTcId() {
        return tcId;
    }

    public void setTcId(int tcId) {
        this.tcId = tcId;
    }

    @Basic
    @Column(name = "tcNum", nullable = false, length = 10)
    public String getTcNum() {
        return tcNum;
    }

    public void setTcNum(String tcNum) {
        this.tcNum = tcNum;
    }

    @Basic
    @Column(name = "tcName", nullable = false, length = 8)
    public String getTcName() {
        return tcName;
    }

    public void setTcName(String tcName) {
        this.tcName = tcName;
    }

    @Basic
    @Column(name = "tcPw", nullable = false, length = 20)
    public String getTcPw() {
        return tcPw;
    }

    public void setTcPw(String tcPw) {
        this.tcPw = tcPw;
    }

    @Basic
    @Column(name = "tcSex", nullable = true, length = 2)
    public String getTcSex() {
        return tcSex;
    }

    public void setTcSex(String tcSex) {
        this.tcSex = tcSex;
    }

    @Basic
    @Column(name = "tcPhone", nullable = true, length = 11)
    public String getTcPhone() {
        return tcPhone;
    }

    public void setTcPhone(String tcPhone) {
        this.tcPhone = tcPhone;
    }

    @Basic
    @Column(name = "tcAvatar", nullable = true, length = 64)
    public String getTcAvatar() {
        return tcAvatar;
    }

    public void setTcAvatar(String tcAvatar) {
        this.tcAvatar = tcAvatar;
    }

    @OneToMany(mappedBy = "tcEntity")
    public Set<CourseEntity> getCozsFromTc() {
        return cozsFromTc;
    }

    public void setCozsFromTc(Set<CourseEntity> cozsFromTc) {
        this.cozsFromTc = cozsFromTc;
    }

    @OneToMany(mappedBy = "tcEntity")
    public Set<TaskEntity> getTasksFromTc() {
        return tasksFromTc;
    }

    public void setTasksFromTc(Set<TaskEntity> tasksFromTc) {
        this.tasksFromTc = tasksFromTc;
    }

    @ManyToOne
    @JoinColumn(name = "depNum", referencedColumnName = "depNum")
    public DepEntity getDepEntity() {
        return depEntity;
    }

    public void setDepEntity(DepEntity depEntity) {
        this.depEntity = depEntity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        TeacherEntity that = (TeacherEntity) o;

        if (tcId != that.tcId) {

            return false;
        }
        if (!Objects.equals(tcNum, that.tcNum)) {

            return false;
        }
        if (!Objects.equals(tcName, that.tcName)) {

            return false;
        }
        if (!Objects.equals(tcPw, that.tcPw)) {

            return false;
        }
        if (!Objects.equals(tcSex, that.tcSex)) {

            return false;
        }
        if (!Objects.equals(tcPhone, that.tcPhone)) {

            return false;
        }
        return Objects.equals(tcAvatar, that.tcAvatar);
    }

    @Override
    public int hashCode() {
        int result = tcId;
        result = 31 * result + (tcNum != null ? tcNum.hashCode() : 0);
        result = 31 * result + (tcName != null ? tcName.hashCode() : 0);
        result = 31 * result + (tcPw != null ? tcPw.hashCode() : 0);
        result = 31 * result + (tcSex != null ? tcSex.hashCode() : 0);
        result = 31 * result + (tcPhone != null ? tcPhone.hashCode() : 0);
        result = 31 * result + (tcAvatar != null ? tcAvatar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "[#" + tcId + ": " + tcNum + ", " + tcName + ", " + tcSex +", " + depEntity.getDepName() + "]";
    }

    public Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>(10);
        map.put("tcId", tcId);
        map.put("tcNum", tcNum);
        map.put("tcName", tcName);
        map.put("tcPw", tcPw);
        map.put("tcSex", tcSex);
        map.put("tcPhone", tcPhone);
        map.put("tcAvatar", tcAvatar);
        map.put("courseSet", cozsFromTc);
        map.put("taskSet", tasksFromTc);
        map.put("depName", depEntity.getDepName());
        return map;
    }
}
