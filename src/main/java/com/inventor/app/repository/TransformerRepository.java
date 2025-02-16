package com.inventor.app.repository;

import com.inventor.app.model.Transformer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransformerRepository extends JpaRepository<Transformer, Long> {

}