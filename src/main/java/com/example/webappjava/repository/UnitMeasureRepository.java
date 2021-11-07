package com.example.webappjava.repository;

import com.example.webappjava.entity.UnitMeasurement;
import com.example.webappjava.enums.UnitName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitMeasureRepository extends JpaRepository<UnitMeasurement, Integer> {
    Optional<UnitMeasurement> findByUnitName(UnitName unitName);
    boolean existsByUnitName(UnitName unitName);
}
