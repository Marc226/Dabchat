package com.example.LoginService.common;

import com.example.LoginService.model.ImagePending;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<ImagePending, String> {
    ImagePending findImagePendingById(String id);
}
