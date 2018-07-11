package com.common.utils.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class HibernateEntityDao<T> extends HibernateDaoSupport {
    @Autowired
    public void setSessionFactoryEx(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    protected Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public HibernateEntityDao() {
        super();
        Type type = this.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) type).getActualTypeArguments();
        this.entityClass = (Class) params[0];
    }

    public void update(T object) {
        getHibernateTemplate().save(object);
        getHibernateTemplate().flush();
    }

    public T findById(Serializable id) {
        return getHibernateTemplate().get(this.entityClass, id);
    }


}
