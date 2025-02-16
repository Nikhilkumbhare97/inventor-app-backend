package com.inventor.app.controller;

import com.inventor.app.model.Transformer;
import com.inventor.app.service.TransformerService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transformer")
@CrossOrigin(origins = "http://localhost:4200")
public class TransformerController {

    @Autowired
    private TransformerService transformerService;

    @PostMapping
    public ResponseEntity<Transformer> saveTransformerDetails(@RequestBody Transformer transformer) {
        Transformer saveTransformerDetails = transformerService.saveTransformerDetails(transformer);
        return new ResponseEntity<>(saveTransformerDetails, HttpStatus.CREATED);
    }

    @PutMapping("/{projectUniqueId}")
    public ResponseEntity<Transformer> updateTransformerDetails(
            @PathVariable("projectUniqueId") Long projectUniqueId,
            @RequestBody Transformer transformer) {
        Transformer updatedTransformerDetails = transformerService.updateTransformerDetails(projectUniqueId,
                transformer);
        return new ResponseEntity<>(updatedTransformerDetails, HttpStatus.OK);
    }

    @GetMapping("/{projectUniqueId}")
    public ResponseEntity<Transformer> getTransformerDetailsById(
            @PathVariable("projectUniqueId") Long projectUniqueId) {
        Optional<Transformer> transformerData = transformerService.getTransformerDetailsById(projectUniqueId);
        return transformerData.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
