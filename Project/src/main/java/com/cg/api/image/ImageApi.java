package com.cg.api.image;

import com.cg.service.upload.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "api/images")
@AllArgsConstructor
public class ImageApi {
    private final UploadService uploadService;

    @PostMapping
    public ResponseEntity<List<Long>> uploadFile(MultipartFile[] files) throws IOException {
        return new ResponseEntity<>(uploadService.uploadAndSaveFile(files), HttpStatus.CREATED);
    }
//    @PostMapping
//    public ResponseEntity<List<Long>> uploadFileByBrandImage(MultipartFile[] files,@PathVariable Long id) throws IOException {
//        return new ResponseEntity<>(uploadService.uploadAndSaveFile(files), HttpStatus.CREATED);
//    }
    public void destroyFile(){

    }
}
