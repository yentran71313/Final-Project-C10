package com.cg.service.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface IUploadService {
    Map destroyFile(String publicId, Map params) throws IOException;

    String uploadFile(MultipartFile multipartFile) throws IOException;
}
