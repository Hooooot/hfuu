package com.hfuu.web.dao;

import com.hfuu.web.entity.TaskEntity;

import java.sql.Timestamp;
import java.util.List;

public interface ITaskDao extends IBaseDao {
    List selectBeforeDeadline(TaskEntity task);
    List selectAfterDeadline(TaskEntity task);
}
