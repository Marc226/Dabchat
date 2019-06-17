package com.example.LoginService.service;

import com.example.LoginService.common.IImageService;
import com.example.LoginService.common.ImageRepository;
import com.example.LoginService.model.ImagePending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService {
    private final ImageRepository repository;

    @Autowired
    public ImageService(ImageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addImage(ImagePending imagePending) {
        repository.save(imagePending);
    }

    @Override
    public ImagePending getImage(String id) {
        return repository.findImagePendingById(id);
    }

    @Override
    public void removeImagePending(String id) {
        ImagePending pending = repository.findImagePendingById(id);
        if(pending != null) {
            repository.delete(pending);
        }
    }
}
