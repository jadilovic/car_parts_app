package com.avlija.parts.service;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avlija.parts.entities.Orders;
import com.avlija.parts.repository.OrdersRepository;
 
@Service
@Transactional
public class OrdersService {
 
    @Autowired
    private OrdersRepository repo;
     
    public List<Orders> listAll() {
        return (List<Orders>) repo.findAll();
    }
     
    public void save(Orders part) {
        repo.save(part);
    }
     
    public Orders get(long ordernum) {
        return repo.findById((long) ordernum).get();
    }
     
    public void delete(long ordernum) {
        repo.deleteById((long) ordernum);
    }
    
    public List<Orders> search(java.util.Date orderdate) {
    	return repo.findByOrderdate(orderdate);
    }
    
    public List<Orders> searchBetween(java.util.Date date1, java.util.Date date2) {
    	return repo.findAllByOrderdateBetween(date1, date2);
    }
    
    public List<Orders> searchFromTo(java.util.Date date1, java.util.Date date2) {
    	return repo.findByFromToDate(date1, date2);
    }
}
