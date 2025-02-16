package com.inventor.app.repository;

import com.inventor.app.model.TransformerConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransformerConfigurationRepository extends JpaRepository<TransformerConfiguration, Long> {
}
