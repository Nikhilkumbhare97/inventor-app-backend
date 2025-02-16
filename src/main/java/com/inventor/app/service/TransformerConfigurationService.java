package com.inventor.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventor.app.model.TransformerConfiguration;
import com.inventor.app.repository.TransformerConfigurationRepository;

@Service
public class TransformerConfigurationService {

    @Autowired
    TransformerConfigurationRepository transformerConfigurationRepository;

    public TransformerConfiguration saveTransformerConfigDetails(TransformerConfiguration transformerConfig) {
        return transformerConfigurationRepository.save(transformerConfig);
    }

    public TransformerConfiguration updateTransformerConfigDetails(Long projectUniqueId,
            TransformerConfiguration transformerConfig) {
        // Ensure the projectUniqueId is preserved
        transformerConfig.setProjectUniqueId(projectUniqueId);
        return transformerConfigurationRepository.save(transformerConfig);
    }

    public Optional<TransformerConfiguration> getTransformerConfigDetailsById(Long projectUniqueId) {
        return transformerConfigurationRepository.findById(projectUniqueId);
    }
}
