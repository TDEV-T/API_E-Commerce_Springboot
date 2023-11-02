package com.ecom.ECommerce.controller;


import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")

public class UploadFile {


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@Param("file") @RequestParam("file") MultipartFile file){
        try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get("src/uploads/"+file.getOriginalFilename());
            Files.write(path,bytes);
            return ResponseEntity.ok("File upload sucessfully!");
        }
        catch(IOException e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
