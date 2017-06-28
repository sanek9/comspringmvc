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
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private UploadStatus index(){
        return new UploadStatus("hello");
    }


//    @GetMapping(path = "/photo")
//    private ModelAndView getNoPhoto(){
//        return new ModelAndView("redirect: /resources/images/240x320_no_photo.jpg");
//    }

//    @GetMapping(path = "/preview")
//    private ModelAndView getNoPreview(){
//        return new ModelAndView("redirect: /resources/images/64x64_no_photo.jpg");
//    }
    @GetMapping(path = {"/photo/{id}", "photo"})
    @ResponseBody
    private void getPhoto(@PathVariable(required = false) String id, HttpServletResponse response) throws IOException {
        InputStream photostream = null;
        photostream = imageService.loadImage(id);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        response.setContentLengthLong(photostream.length());
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(photostream, outputStream);
        photostream.close();
        outputStream.close();
    }
    @GetMapping(path = {"/preview/{id}","/preview"}, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    private void getPreview(@PathVariable(required = false)  String id, HttpServletResponse response) throws IOException {
        InputStream photostream = null;

        photostream = imageService.loadPreview(id);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        response.setContentLengthLong(photostream.length());
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(photostream, outputStream);
        photostream.close();
        outputStream.close();
    }
    @PostMapping(path = "/uphoto", produces = "application/json")
    @ResponseBody
    private UploadStatus uploadPhoto(@RequestParam("file") MultipartFile file){

        System.out.println("upload photo");
        if(file.isEmpty()) return new UploadStatus("fail");
        try {
            InputStream inputStream = file.getInputStream();
            String id = imageService.saveImage(inputStream);
            inputStream.close();
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
