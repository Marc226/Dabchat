package com.example.demo.service;

import com.example.demo.model.ImageModel;
import com.example.demo.model.ImageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

public class ServiceRoutes {
    @Autowired
    private ImageService imageService;

    @CrossOrigin
    @RequestMapping("/image/get")
    public ResponseEntity<ImageModel> getPendingImages(@Valid @RequestBody ImageRequest request){
        return imageService.getPendingImage(request);
    }

    @CrossOrigin
    @RequestMapping("/image/upload")
    public ResponseEntity<ImageModel> uploadImage(@Valid @RequestBody ImageModel image){
        return imageService.saveImage(image);
    }


}

