package com.myspringmvc.core;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sanek9 on 27.06.17.
 */
public interface StorageService {
    void save(String id, InputStream is) throws IOException;
    String save(InputStream is) throws IOException;
    InputStream load(String id);

}
