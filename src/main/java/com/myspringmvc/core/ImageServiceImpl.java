package com.myspringmvc.core;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * Created by sanek9 on 27.06.17.
 */
@Service
@Repository
public class ImageServiceImpl implements ImageService {

    @Autowired
    private StorageService storageService;


    public void saveImage(String id, byte[] bis) {

    }

    public String saveImage(byte[] bytes) throws IOException {

        BufferedImage image = null;
        try {
            image = ImageIO.read(new ByteArrayInputStream(bytes));
//            image = Thumbnails.of(new ByteArrayInputStream(bytes)).asBufferedImage();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            Thumbnails.of(image).crop(Positions.CENTER).size(240,320).outputFormat("jpg").toOutputStream(bos);
            String id = storageService.save(bos.toByteArray());
            bos = new ByteArrayOutputStream();
            Thumbnails.of(image).crop(Positions.CENTER).size(64,64).outputFormat("jpg").toOutputStream(bos);
            storageService.save("64x64_"+id, bos.toByteArray());
            return id;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (IllegalStateException e){
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }
    }

    public byte[] loadPreview(String id) {
        return storageService.load("64x64_"+id);
    }

    public byte[] loadImage(String id) {
        return storageService.load(id);
    }
}
