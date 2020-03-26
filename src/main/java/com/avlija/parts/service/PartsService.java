package com.avlija.parts.service;

import java.util.List;
import java.util.Optional;

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
        return (List<Parts>) repo.findAll();
    }
     
    public void save(Parts part) {
        repo.save(part);
    }
     
    public void delete(String barcode) {
        repo.deleteById(barcode);
    }
    
    public List<Parts> search(String keyword) {
    	return repo.search(keyword);
    }
    
    public List<Parts> get(String sifra) {
    	return repo.search(sifra);
    }
}
