package com.myspringmvc.core;

import com.myspringmvc.entity.BytesEntity;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sanek9 on 27.06.17.
 */
@Service
public class HibernateStorageServiceImpl implements StorageService {

    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void save(String id, InputStream is) throws IOException {
        BytesEntity bytesEntity = new BytesEntity();
        bytesEntity.setId(id);
        bytesEntity.setBytes(IOUtils.toByteArray(is));
        entityManager.merge(bytesEntity);
    }
    @Transactional
    public String save(InputStream is) throws IOException {
        BytesEntity bytesEntity = new BytesEntity();
        bytesEntity.setBytes(IOUtils.toByteArray(is));
        entityManager.persist(bytesEntity);
        return bytesEntity.getId();
    }

    @Transactional
    public InputStream load(String id) {
        BytesEntity bytesEntity = entityManager.find(BytesEntity.class, id);

        return new ByteArrayInputStream(bytesEntity.getBytes());
    }
}
