package com.myspringmvc.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by sanek9 on 27.06.17.
 */
public interface ImageService {
    void saveImage(String id, InputStream is);
    String saveImage(InputStream is) throws IOException;
    InputStream loadPreview(String id) throws IOException;
    InputStream loadImage(String id) throws IOException;
}
