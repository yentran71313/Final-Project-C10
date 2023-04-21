package com.cg.service.upload;


import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadService implements IUploadService{

    private final Cloudinary cloudinary;

    @Override
    public Map destroyFile(String publicId, Map params) throws IOException {
        params.put("invalidate",true);
        return cloudinary.uploader().destroy(publicId,params);
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        String uuid = UUID.randomUUID().toString();
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id",uuid))
                .get("url")
                .toString() + "="+ uuid;
    }
}
