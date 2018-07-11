package com.module.userinfo.service.impl;

import BaseSpringTest.JUnitServiceBase;
import com.common.utils.dao.CommonHibernateDao;
import com.module.userinfo.entity.User;
import com.module.userinfo.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.util.List;

/**
 * Created by wangji on 2017/8/11.
 */
@Slf4j
public class UserInfoServiceTest extends JUnitServiceBase{

    @Autowired
    public IUserInfoService iUserInfoService;
    @Autowired
    public CommonHibernateDao commonHibernateDao;

    @Test
    public void Test1(){
        iUserInfoService.save(null);
    }
    @Test
    public  void test12() {
        final DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.setProjection(Projections.projectionList().
                add(Projections.alias(Projections.rowCount(),"count"))
                .add(Projections.alias(Projections.groupProperty("name"),"name")))
                .setResultTransformer(Transformers.TO_LIST);
        List list =commonHibernateDao.findALlByDetachedCriteria(detachedCriteria);
        log.info(list.toString());
    }

    @Test
    public void Test3(){
        Integer count = commonHibernateDao.findCountByCriteria(DetachedCriteria.forClass(User.class));
        log.info(count.toString());
    }

    @Test
    public void Test4(){
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start("A");
            Thread.sleep(200);
            stopWatch.stop();
            log.info(stopWatch.prettyPrint());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}