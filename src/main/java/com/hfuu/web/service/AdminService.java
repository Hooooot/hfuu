package com.hfuu.web.service;

import org.springframework.stereotype.Service;

/**
 * @Decription: DAO顶层接口的实现，继承Spring提供的hibernate模板
 * @CreateDate: 2019年9月25日 00点17分
 * @Author: whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 */
@Service
public interface AdminService<T> extends BaseService<T> {
}
