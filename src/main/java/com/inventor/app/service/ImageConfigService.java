package com.inventor.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventor.app.model.ImageConfig;
import com.inventor.app.repository.ImageConfigRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ImageConfigService {

    @Autowired
    private ImageConfigRepository repository;

    public ImageConfig getImageConfig(String imageName) {
        Optional<ImageConfig> imageConfig = repository.findById(imageName);
        return imageConfig.orElse(null); // Or throw an exception if you prefer
    }

    public List<String> getAllImageNames() {
        return repository.getAllImageNames();
    }

    public ImageConfig updateImageConfig(ImageConfig imageConfig) {
        return repository.save(imageConfig); // Saves or updates based on ID
    }

}