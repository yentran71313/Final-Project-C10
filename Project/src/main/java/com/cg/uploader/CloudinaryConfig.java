package com.cg.uploader;

import com.cloudinary.Cloudinary;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    private final String CLOUD_NAME= "dohew0ngz";
    private final String API_KEY = "593434591814319";
    private final String API_SECRET = "tCQSUwZgd0yWtokcnTeelJiKo1M";

    @Bean
    public Cloudinary cloudinary(){
        Map<String,String> config = new HashMap<>();
        config.put("cloud_name",CLOUD_NAME);
        config.put("api_key",API_KEY);
        config.put("api_secret",API_SECRET);
        return new Cloudinary(config);
    }



}
