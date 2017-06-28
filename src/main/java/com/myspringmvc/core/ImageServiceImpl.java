package com.myspringmvc.core;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by sanek9 on 27.06.17.
 */
@Service
@Repository
public class ImageServiceImpl implements ImageService {


    @Autowired
    private StorageService storageService;
    @Autowired
    ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private ApplicationContext appContext;


    private class ProcessImage implements Runnable{

        private Thumbnails.Builder<?> builder;
        OutputStream outputStream;
        Exception exception;
        public ProcessImage(Thumbnails.Builder<?> builder, OutputStream outputStream ){
            this.builder = builder;
            this.outputStream = outputStream;
        }

        public Exception getException() {
            return exception;
        }
        public boolean hasException(){
            return (exception!=null);
        }
        public void run() {
            System.out.println("run");
            try {
                builder.toOutputStream(outputStream);
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;
            }
        }

    }

    public void saveImage(String id, InputStream is) {

    }

    public String saveImage(InputStream is) throws IOException {

        BufferedImage image = null;
        try {
            image = ImageIO.read(is);

//            image = Thumbnails.of(new ByteArrayInputStream(bytes)).asBufferedImage();
            PipedOutputStream o1 = new PipedOutputStream();
            PipedInputStream i1 = new PipedInputStream(o1);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


//            ProcessImage processImage1 = new ProcessImage(Thumbnails.of(image).crop(Positions.CENTER).size(240, 320).outputFormat("jpg"),o1);
//            taskExecutor.execute(processImage1);
            Thumbnails.of(image).crop(Positions.CENTER).size(240,320).outputFormat("jpg").toOutputStream(byteArrayOutputStream);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            String id = storageService.save(byteArrayInputStream);


            PipedOutputStream o2 = new PipedOutputStream();
            PipedInputStream i2 = new PipedInputStream(o2);
//            ProcessImage processImage2 = new ProcessImage(Thumbnails.of(image).crop(Positions.CENTER).size(64, 64).outputFormat("jpg"),o2);
//            taskExecutor.execute(processImage2);
            //            new Thread(processImage2).start();
            byteArrayOutputStream = new ByteArrayOutputStream();
            Thumbnails.of(image).crop(Positions.CENTER).size(64,64).outputFormat("jpg").toOutputStream(byteArrayOutputStream);
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            storageService.save("64x64_"+id, byteArrayInputStream);
            return id;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (IllegalStateException e){
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }
    }

    public InputStream loadPreview(String id) throws IOException {
        try{
            if(id==null) throw new NullPointerException();
            return storageService.load("64x64_"+id);
        }catch (NullPointerException e){
            return appContext.getResource("/resources/images/64x64_no_photo.jpg").getInputStream();
        }
    }

    public InputStream loadImage(String id) throws IOException {
            try {
                if(id==null) throw new NullPointerException();
                return storageService.load(id);

            }catch (NullPointerException e){
                return appContext.getResource("/resources/images/240x320_no_photo.jpg").getInputStream();
            }
    }
}
