package com.inventor.app.controller;

import com.inventor.app.model.TransformerConfiguration;
import com.inventor.app.service.TransformerConfigurationService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transformer-config")
@CrossOrigin(origins = "http://localhost:4200")
public class TransformerConfigurationController {
        @Autowired
        private TransformerConfigurationService transformerConfigurationService;

        @PostMapping
        public ResponseEntity<TransformerConfiguration> saveTransformerConfigDetails(
                        @RequestBody TransformerConfiguration transformerConfiguration) {
                TransformerConfiguration saveTransformerDetails = transformerConfigurationService
                                .saveTransformerConfigDetails(transformerConfiguration);
                return new ResponseEntity<>(saveTransformerDetails, HttpStatus.CREATED);
        }

        @PutMapping("/{projectUniqueId}")
        public ResponseEntity<TransformerConfiguration> updateTransformerDetails(
                        @PathVariable("projectUniqueId") Long projectUniqueId,
                        @RequestBody TransformerConfiguration transformer) {
                TransformerConfiguration updatedTransformerDetails = transformerConfigurationService
                                .updateTransformerConfigDetails(projectUniqueId,
                                                transformer);
                return new ResponseEntity<>(updatedTransformerDetails, HttpStatus.OK);
        }

        @GetMapping("/{projectUniqueId}")
        public ResponseEntity<TransformerConfiguration> getTransformerDetailsById(
                        @PathVariable("projectUniqueId") Long projectUniqueId) {
                Optional<TransformerConfiguration> transformerData = transformerConfigurationService
                                .getTransformerConfigDetailsById(projectUniqueId);
                return transformerData.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
}
