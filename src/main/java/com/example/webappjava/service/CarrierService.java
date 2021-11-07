package com.example.webappjava.service;

import com.example.webappjava.entity.Carrier;
import com.example.webappjava.repository.CarrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarrierService {

    @Autowired
    CarrierRepository carrierRepository;

    public List<Carrier> list() {
        return carrierRepository.findAll();
    }
    public Optional<Carrier> getOne(int id){
        return carrierRepository.findById(id);
    }

    public void  save(Carrier carrier){
        carrierRepository.save(carrier);
    }

    public void delete(int id){
        carrierRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return carrierRepository.existsById(id);
    }
}
