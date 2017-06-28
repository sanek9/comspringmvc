package com.myspringmvc.core;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by sanek9 on 27.06.17.
 */
public interface ImageService {
    void saveImage(String id, byte[] bytes);
    String saveImage( byte[] bytes) throws IOException;
    byte[] loadPreview(String id);
    byte[] loadImage(String id);
}
