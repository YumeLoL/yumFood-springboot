package com.yumfood.controller;

import com.yumfood.common.R;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * file upload and download
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${yumFood.path}") // config path in yml
    private String basePath;

    /**
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        log.info(file.toString());

        // get original filename suffix
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // UUID, new filename
        String fileName = UUID.randomUUID() + suffix;//dfsdfdfd.jpg，

        // create a dir used to store images
        // basePath
        File dir = new File(basePath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        // transfer file to the basePath location
        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return R.success(fileName);
    }

    /**
     * file download
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){

        try {
            //inputStream read file
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

            //outputStream write back to the browser
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg"); // set file format

            // read - write
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            //close
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
