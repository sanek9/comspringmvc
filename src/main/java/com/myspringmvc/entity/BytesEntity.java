package com.myspringmvc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by sanek9 on 27.06.17.
 */
@Entity
public class BytesEntity {
    @Id
    @GeneratedValue(generator = "owng")
    @GenericGenerator(name = "owng", strategy = "com.myspringmvc.core.OwnGenerator")
//    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="IdOrGenerated")
//    @GenericGenerator(name="IdOrGenerated",
//            strategy="....UseIdOrGenerate"
//    )
    private String id;

    private byte[] bytes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
