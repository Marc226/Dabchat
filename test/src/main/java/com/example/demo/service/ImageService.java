package com.example.demo.service;

import com.example.demo.common.IImageService;
import com.example.demo.model.ImageModel;
import com.example.demo.model.ImageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ImageService {

    @Autowired
    private IImageService service;


    public ResponseEntity<ImageModel> saveImage(ImageModel user) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    public ResponseEntity<ImageModel> getPendingImage(ImageRequest request) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
