package com.myspringmvc.core;

import com.myspringmvc.entity.BytesEntity;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by sanek9 on 27.06.17.
 */
@Service
public class HibernateStorageServiceImpl implements StorageService {

    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void save(String id, byte[] bytes) {
        BytesEntity bytesEntity = new BytesEntity();
        bytesEntity.setId(id);
        bytesEntity.setBytes(bytes);
        entityManager.merge(bytesEntity);
    }
    @Transactional
    public String save(byte[] bytes) {
        BytesEntity bytesEntity = new BytesEntity();
        bytesEntity.setBytes(bytes);
        entityManager.persist(bytesEntity);
        return bytesEntity.getId();
    }

    @Transactional
    public byte[] load(String id) {
        BytesEntity bytesEntity = entityManager.find(BytesEntity.class, id);
        return bytesEntity.getBytes();
    }
}
