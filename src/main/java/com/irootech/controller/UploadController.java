package com.irootech.controller;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * Created by chen on 2018/4/12 0012.
 */
@Controller
public class UploadController extends HttpServlet {
//   private static Logger logger =  Logger.getLogger(UploadController.class);
   //上传文件的页面跳转
    @RequestMapping("/gotoUpLoadFile")
   public String gotoUpLoadFile(){

        return "upload";
   }

   //上传文件的处理方法
    @RequestMapping("/upLoadFile")
    @ResponseBody
    public String upLoad(HttpServletRequest request, @RequestParam("file") MultipartFile file){

        String originalFilename = file.getOriginalFilename();//文件的真是名字即xxx.img啊等后缀
        String contentType = file.getContentType();
        long size = file.getSize();
        String name = file.getName();//form表单的name属性

        String canonicalPath = null;
        try {
            canonicalPath = new File("").getCanonicalPath();//项目路径
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String path = this.getClass().getResource("/").getPath();//类的加载路径
        String realpath = canonicalPath+"/src/main/resources/static/";

        try {
            file.transferTo(new File(realpath+originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "upLoadFileSuccess";
    }


}

