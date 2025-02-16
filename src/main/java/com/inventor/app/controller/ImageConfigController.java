package com.inventor.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inventor.app.model.ImageConfig;
import com.inventor.app.service.ImageConfigService;

import java.util.List;

@RestController
@RequestMapping("/api/config")
public class ImageConfigController {

    @Autowired
    private ImageConfigService service;

    @GetMapping("/{imageName}")
    public ResponseEntity<ImageConfig> getImageConfig(@PathVariable("imageName") String imageName) { // Add "imageName"
                                                                                                     // here
        ImageConfig imageConfig = service.getImageConfig(imageName);
        if (imageConfig != null) {
            return ResponseEntity.ok(imageConfig);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/images") // Get all image names
    public ResponseEntity<List<String>> getAllImageNames() {
        List<String> imageNames = service.getAllImageNames();
        return ResponseEntity.ok(imageNames);
    }

    @PutMapping("/{imageName}")
    public ResponseEntity<ImageConfig> updateImageConfig(
            @PathVariable("imageName") String imageName,
            @RequestBody ImageConfig imageConfig) {

        if (!imageName.equals(imageConfig.getImageName())) {
            return ResponseEntity.badRequest().build();
        }

        ImageConfig updatedConfig = service.updateImageConfig(imageConfig);
        return ResponseEntity.ok(updatedConfig);
    }

}