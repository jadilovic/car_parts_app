package com.avlija.parts.service;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avlija.parts.entities.Parts;
import com.avlija.parts.repository.PartsRepository;
 
@Service
@Transactional
public class PartsService {
 
    @Autowired
    private PartsRepository repo;
     
    public List<Parts> listAll() {
        return repo.findAll();
    }
     
    public void save(Parts part) {
        repo.save(part);
    }
     
    public Parts get(String sifra) {
        return repo.findById(sifra).get();
    }
     
    public void delete(String barcode) {
        repo.deleteById(barcode);
    }
}
