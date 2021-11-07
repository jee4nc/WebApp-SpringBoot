package com.example.webappjava.service;

import com.example.webappjava.entity.Carrier;
import com.example.webappjava.entity.Supplier;
import com.example.webappjava.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public List<Supplier> list() {
        return supplierRepository.findAll();
    }
    public Optional<Supplier> getOne(int id){
        return supplierRepository.findById(id);
    }

    public void  save(Supplier supplier){
        supplierRepository.save(supplier);
    }

    public void delete(int id){
        supplierRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return supplierRepository.existsById(id);
    }
}
