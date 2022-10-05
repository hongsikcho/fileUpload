package com.ll.exam.app10.app.fileUpload.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class FileUploadController {
    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;


    @RequestMapping("")
    @ResponseBody
    public String upload(@RequestParam("img1")MultipartFile img1 ,@RequestParam("img1")MultipartFile img2) {


        File file1 = new File(genFileDirPath+"/1.png");
        File file2 = new File(genFileDirPath+"/1.png");
        try {
            img1.transferTo(file1);
            img2.transferTo(file1);
        } catch (IOException e) {
            throw new RuntimeException("안돼");
        }

        return "업로드 완료";
    }
}
