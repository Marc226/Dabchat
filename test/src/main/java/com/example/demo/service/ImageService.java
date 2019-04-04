package com.example.demo.service;

import com.example.demo.common.IImageService;
import com.example.demo.model.ImageModel;
import com.example.demo.model.ImageRequest;
import org.springframework.http.ResponseEntity;

public class ImageService implements IImageService {
    @Override
    public ResponseEntity<ImageModel> saveImage(ImageModel user) {
        return null;
    }

    @Override
    public ResponseEntity<ImageModel> getPendingImage(ImageRequest request) {
        return null;
    }
}
