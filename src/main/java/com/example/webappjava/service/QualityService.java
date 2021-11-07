package com.example.webappjava.service;

import com.example.webappjava.entity.Quality;
import com.example.webappjava.enums.QualityName;
import com.example.webappjava.repository.QualityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QualityService {

    @Autowired
    QualityRepository qualityRepository;

    public List<Quality> list() {return qualityRepository.findAll();}
    public void save(Quality quality) {qualityRepository.save(quality);}
    public Optional<Quality> getByQualityName(QualityName qualityName) {
        return  qualityRepository.findByQualityName(qualityName);
    }
    public boolean existsByQualityName(QualityName qualityName) {
        return qualityRepository.existsByQualityName(qualityName);
    }
}
