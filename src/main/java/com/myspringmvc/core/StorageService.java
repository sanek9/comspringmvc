package com.myspringmvc.core;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

/**
 * Created by sanek9 on 27.06.17.
 */
public interface StorageService {
    void save(String id, byte[] bytes);
    String save(byte[] bytes);
    byte[] load(String id);

}
