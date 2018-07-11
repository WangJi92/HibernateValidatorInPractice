package com.module.userinfo.dao;

import com.common.utils.dao.HibernateEntityDao;
import com.module.userinfo.entity.User;
import com.module.userinfo.entity.Work;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * descrption:
 * authohr: wangji
 * date: 2017-08-11 14:45
 */
@Repository
@SuppressWarnings("unchecked")
public class UserDao extends HibernateEntityDao<User>{


    public User findById(Integer id){
     return super.findById(id);
    }

    public void save(User u){
        super.update(u);
    }
    public  void test(String address,String userName){
        final DetachedCriteria work = DetachedCriteria.forClass(Work.class);
        work.add(Restrictions.like("name","%"+address+"%"));
        work.setProjection(Property.forName("id"));

        final  DetachedCriteria user = DetachedCriteria.forClass(User.class);
        user.add(Property.forName("workId").in(work));
        user.add(Restrictions.like("name","%"+userName+"%"));
        List<User> userList =getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<User>>() {
            @Override
            public List<User> doInHibernate(Session session) throws HibernateException {
                Criteria criteria = user.getExecutableCriteria(session);
                criteria
                        .setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
                return (List<User>)criteria.list();
            }
        });
        System.out.println("dddddddddddddddd"+userList.toString());
    }


}
