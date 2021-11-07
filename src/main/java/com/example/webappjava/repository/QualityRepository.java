package com.example.webappjava.repository;

import com.example.webappjava.entity.Quality;
import com.example.webappjava.enums.QualityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QualityRepository extends JpaRepository<Quality, Integer> {
    Optional<Quality> findByQualityName(QualityName qualityName);
    boolean existsByQualityName(QualityName qualityName);
}
