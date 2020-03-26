package com.avlija.parts.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.avlija.parts.entities.Orders;
import com.avlija.parts.entities.Parts;
 
public interface PartsRepository extends CrudRepository<Parts, String>{

	@Query("select p from Parts p where p.grupa like %?1")
	public List<Parts> search(String keyword);
}
