package com.ecom.ECommerce.controller;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")

public class UploadFile {


    private String newFileName;

    @PostMapping("/upload")
    public ResponseEntity<Map<String,String>> uploadFile(@Param("file") @RequestParam("file") MultipartFile file){
        try{
            byte[] bytes = file.getBytes();
            String originalFileName = file.getOriginalFilename();

            String ext = getFileNameExt(originalFileName);
            String newFileName = UUID.randomUUID().toString()+"."+ext;

           Path path = Paths.get("src/uploads/"+newFileName);
           Files.write(path,bytes);
           Map<String,String> res = new HashMap<>();
           res.put("status","success");
           res.put("data",newFileName);

           return ResponseEntity.ok(res);
        }
        catch(IOException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("status","error"));
        }
    }


    @GetMapping("/images/{imageName}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable String imageName) throws IOException {


        Path imagePath = Paths.get("src/uploads/"+imageName);


        if(Files.exists(imagePath)){
            HttpHeaders headers = new HttpHeaders();

            String mimetype = Files.probeContentType(imagePath);
            headers.add(HttpHeaders.CONTENT_TYPE,mimetype);

            byte[] imageBtyes = Files.readAllBytes(imagePath);
            ByteArrayResource resource = new ByteArrayResource(imageBtyes);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    private String getFileNameExt(String fileName){

        int dotIndex = fileName.lastIndexOf('.');

        if(dotIndex  >= 0){
            return fileName.substring(dotIndex + 1);

        }
        return "";
    }
}

