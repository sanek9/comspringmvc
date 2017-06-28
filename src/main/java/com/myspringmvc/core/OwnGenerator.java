package com.myspringmvc.core;

import com.myspringmvc.entity.BytesEntity;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.id.UUIDHexGenerator;

import java.io.Serializable;

/**
 * Created by sanek9 on 28.06.17.
 */
public class OwnGenerator extends UUIDHexGenerator{

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (object == null) throw new HibernateException(new NullPointerException()) ;

        if ((((BytesEntity) object).getId()) == null) {
            Serializable id = super.generate(session, object) ;
            return id;
        } else {
            return ((BytesEntity) object).getId();

        }
    }
}
