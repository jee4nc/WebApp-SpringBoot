package com.example.webappjava.service;

import com.example.webappjava.entity.UnitMeasurement;
import com.example.webappjava.enums.UnitName;
import com.example.webappjava.repository.UnitMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UnitMeasureService {

    @Autowired
    UnitMeasureRepository unitMeasureRepository;

    public List<UnitMeasurement> list() {return unitMeasureRepository.findAll();}
    public void save(UnitMeasurement unitMeasurement) {
        unitMeasureRepository.save(unitMeasurement);
    }

    public Optional<UnitMeasurement> getByUnitName(UnitName unitName) {
        return unitMeasureRepository.findByUnitName(unitName);
    }

    public boolean existsByUnitName(UnitName unitName) {
        return unitMeasureRepository.existsByUnitName(unitName);
    }
}
