package com.cg.service.upload;


import com.cg.model.Image;
import com.cg.repository.ImageRepository;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadService implements IUploadService{

    private final Cloudinary cloudinary;

    private final ImageRepository imageRepository;

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

    public List<Long> uploadAndSaveFile(MultipartFile[] multipartFiles) throws IOException {

        List<Long> images = new ArrayList<>();
        for (MultipartFile multipartFile: multipartFiles
             ) {
            String uuid = UUID.randomUUID().toString();
            String str = cloudinary.uploader()
                    .upload(multipartFile.getBytes(),
                            Map.of("public_id",uuid))
                    .get("url")
                    .toString() + "="+ uuid;
            String fileUrl = str.split("=")[0];
            String cloudId = str.split("=")[1];
            Image image = new Image();
            image.setCloudId(cloudId);
            image.setFileUrl(fileUrl);
            image = imageRepository.save(image);
            images.add(image.getId());
        }


        return images;
    }
}
