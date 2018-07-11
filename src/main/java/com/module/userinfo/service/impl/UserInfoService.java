package com.module.userinfo.service.impl;


import com.common.utils.dao.CommonHibernateDao;
import com.common.validator.Aop.EgValidator.EgValidate;
import com.common.validator.Group.Identification.Second;
import com.module.userinfo.dao.UserDao;
import com.module.userinfo.entity.User;
import com.module.userinfo.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;


/**
 * descrption:简单的实现服务类
 * authohr: wangji
 * date: 2017-07-31 18:00
 */
@Service
@Slf4j
@EgValidate
public class UserInfoService implements IUserInfoService {


    @Resource
    private UserDao userDao;
    @Autowired
    private CommonHibernateDao commonDao;

    @Override
    public User getUserInfoById(Integer id) {
        return userDao.findById(id);
    }
    @Autowired
    public Validator validator;

    @EgValidate(groups = {Second.class})
    @Override
    public void save(User user) {
        Set<ConstraintViolation<User>> set = validator.validate(user);
        userDao.save(user);
    }



    @Override
    public List<User> findAll(){
        String sql = "select u.userName as name,u.userAge as age,u.userAddress as address,u.id from user u";
        List<User> list =commonDao.findListBySQL(sql,User.class);
        return list;
    }

    public void test(String address,String name){
        userDao.test(address,name);
    }

}
