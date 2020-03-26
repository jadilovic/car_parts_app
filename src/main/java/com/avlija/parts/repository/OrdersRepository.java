package com.avlija.parts.repository;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.avlija.parts.entities.Orders;

public interface OrdersRepository extends CrudRepository<Orders, Long>{
 
    List<Orders> findAllByOrderdateBetween(
    	      Date date1,
    	      Date date2);
    
    List<Orders> findByOrderdate(Date orderdate);
	
    @Query("select a from Orders a where a.orderdate = :orderdate")
    List<Orders> findAllByOrderDate(
      @Param("orderdate") Date orderdate);
    
    @Query(value = "SELECT * FROM Orders n WHERE n.orderdate >= :fromDate AND n.orderdate <= :toDate",
            nativeQuery = true)
    	List<Orders> findByFromToDate(
    			@Param("fromDate") Date fromDate,
    			@Param("toDate") Date toDate);
	
}
