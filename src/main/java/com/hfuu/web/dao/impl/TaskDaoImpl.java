package com.hfuu.web.dao.impl;

import com.hfuu.web.dao.ITaskDao;
import com.hfuu.web.entity.TaskEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository("taskDAO")
public class TaskDaoImpl extends BaseDaoImpl implements ITaskDao {
    @Override
    public void updateById(Object entity) {
        TaskEntity task = (TaskEntity) entity;
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update TaskEntity e set e.tcNum=:tcNum,e.cozNum=:cozNum,e.deadline=:deadline," +
                "e.pubTime=:pubTime,e.taskDesc=:taskDesc,e.taskName=:taskName where e.taskId=:taskId";
        Query query = session.createQuery(hql);
        query.setProperties(task);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "delete from TaskEntity e where e.taskId=?1";
        Query query = session.createQuery(hql);
        query.setParameter(1, id);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public List select(Object entity) {
        TaskEntity task = (TaskEntity) entity;
        Session session = sessionFactory.getCurrentSession();
        String hql = null;
        if (task.getTaskId() != 0){
            hql = "from TaskEntity e where e.taskId = :taskId";
        }else if (task.getCozNum() != null && task.getTcNum()!=null && task.getTaskName()!=null){
            hql = "from TaskEntity e where e.tcNum = :tcNum and cozNum=:cozNum and e.taskName=:taskName";
        }else if (task.getCozNum() != null && task.getTcNum()!=null && task.getTaskName()==null){
            hql = "from TaskEntity e where e.tcNum = :tcNum and cozNum=:cozNum";
        }
        Query query = session.createQuery(hql);
        query.setProperties(task);
        return query.list();
    }

    @Override
    public List getAll() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from TaskEntity ";
        Query query = session.createQuery(hql);
        List list = query.list();
        transaction.commit();
        return list;
    }

    @Override
    public boolean isExist(Object entity) {
        return this.select(entity).size() > 0;
    }

    @Override
    public List selectBeforeDeadline(TaskEntity task) {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        if (task.getTcNum()!=null && task.getCozNum()!=null){
            String hql = "from TaskEntity e where e.deadline != null and e.deadline>?1 and e.cozNum=?2 and e.tcNum=?3";
            return getList(task, ts, session, transaction, hql);
        }
        return null;
    }

    @Override
    public List selectAfterDeadline(TaskEntity task) {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (task.getTcNum()!=null && task.getCozNum()!=null){
            String hql = "from TaskEntity e where e.deadline != null and e.deadline<?1 and e.cozNum=?2 and e.tcNum=?3";
            return getList(task, ts, session, transaction, hql);
        }
        return null;
    }

    private List getList(TaskEntity task, Timestamp ts, Session session, Transaction transaction, String hql) {
        Query query = session.createQuery(hql);
        query.setParameter(1, ts);
        query.setParameter(2, task.getCozNum());
        query.setParameter(3, task.getTcNum());
        List list = query.list();
        transaction.commit();
        return list;
    }
}
