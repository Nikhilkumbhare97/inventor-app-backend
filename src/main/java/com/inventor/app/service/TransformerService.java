package com.inventor.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventor.app.model.Transformer;
import com.inventor.app.repository.TransformerRepository;

@Service
public class TransformerService {

    @Autowired
    private TransformerRepository transformerRepository;

    public Transformer saveTransformerDetails(Transformer transformer) {
        return transformerRepository.save(transformer);
    }

    public Transformer updateTransformerDetails(Long projectUniqueId, Transformer transformer) {
        return transformerRepository.findById(projectUniqueId)
                .map(existingTransformerDetails -> {
                    // Update only non-null fields
                    if (transformer.getTransformerType() != null) {
                        existingTransformerDetails.setTransformerType(transformer.getTransformerType());
                    }
                    if (transformer.getDesignType() != null) {
                        existingTransformerDetails.setDesignType(transformer.getDesignType());
                    }
                    return transformerRepository.save(existingTransformerDetails);
                })
                .orElseThrow(() -> new RuntimeException(
                        "Transformer details not found with projectUniqueId: " + projectUniqueId));
    }

    public Optional<Transformer> getTransformerDetailsById(Long projectUniqueId) {
        return transformerRepository.findById(projectUniqueId);
    }
}
