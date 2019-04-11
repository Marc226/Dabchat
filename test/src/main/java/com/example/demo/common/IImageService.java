package com.example.demo.common;

import com.example.demo.model.ImageModel;
import com.example.demo.model.ImageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;

public interface IImageService extends MongoRepository<ImageModel, String> {
    ResponseEntity<ImageModel> saveImage(ImageModel user);
    ResponseEntity<ImageModel> getPendingImage(ImageRequest request);
}
