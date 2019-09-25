package com.hfuu.web.dao.impl;

import com.hfuu.web.dao.StuDao;
import com.hfuu.web.dao.base.BaseDaoImpl;
import com.hfuu.web.entity.StudentEntity;
import org.springframework.stereotype.Repository;

/**
 * 描述：
 *
 * @author: Ciel-08
 * 创建时间：2019/9/25 18:59
 * 最后修改时间：
 * 最后修改人：
 */
@Repository("stuDao")
public class StuDaoImpl extends BaseDaoImpl<StudentEntity> implements StuDao {
}
