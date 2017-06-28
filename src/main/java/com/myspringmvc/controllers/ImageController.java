package com.myspringmvc.controllers;

import com.myspringmvc.MyUser;
import com.myspringmvc.core.ImageService;
import com.myspringmvc.core.PersonDetailsManager;
import com.myspringmvc.core.PersonDetailsService;
import com.myspringmvc.entity.Person;
import com.myspringmvc.json.UploadStatus;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Created by sanek9 on 27.06.17.
 */
@Controller
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private PersonDetailsManager personDetailsManager;
    @RequestMapping()
    @ResponseBody
    private String index(){
        return "hello";
    }


    @GetMapping(path = "/photo/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    private byte[] getPhoto(@PathVariable String id){
        return imageService.loadImage(id);
    }
    @GetMapping(path = "/preview/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    private byte[] getPreview(@PathVariable String id){
        return imageService.loadPreview(id);
    }
    @PostMapping(path = "/uphoto", produces = "application/json")
    @ResponseBody
    private UploadStatus uploadPhoto(@RequestParam("file") MultipartFile file){

        if(file.isEmpty()) return new UploadStatus("fail");
        try {
            String id = imageService.saveImage(file.getBytes());
            MyUser user = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Person person = personDetailsManager.findById(user.getPersonId());
            person.setPhotoId(id);
            personDetailsManager.updatePerson(person);

            return new UploadStatus("ok", id);
        } catch (IOException e) {
            e.printStackTrace();
            return new UploadStatus("fail");
        }
    }
    @PostMapping(value = "/uphoto2" )
    @ResponseBody
    private String uploadPhoto2(@RequestParam("file") MultipartFile file){


        System.out.println("uploadPhoto");
        if (file.isEmpty()) {
            return "{status: file_is_empty}";
        }
        File dir = new File("");
        dir.mkdirs();
        try {
            File dfile = new File(dir, String.valueOf(new Random().nextInt()));
            dfile.createNewFile();
            InputStream inputStream = file.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(dfile);
            IOUtils.copy(inputStream, fileOutputStream);
            inputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "{status: fail}";
        }
        return "{status: ok}";
    }
}
