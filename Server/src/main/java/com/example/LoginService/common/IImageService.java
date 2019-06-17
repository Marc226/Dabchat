package com.example.LoginService.common;

import com.example.LoginService.model.ImagePending;

public interface IImageService {
    void addImage(ImagePending imagePending);
    ImagePending getImage(String id);
    void removeImagePending(String id);
}
