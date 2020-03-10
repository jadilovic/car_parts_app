package com.avlija.parts.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.avlija.parts.entities.Parts;
 
public interface PartsRepository extends JpaRepository<Parts, String> {
 
}
