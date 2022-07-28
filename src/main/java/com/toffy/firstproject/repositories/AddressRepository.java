package com.toffy.firstproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.toffy.firstproject.models.Address;
import com.toffy.firstproject.models.Employee;

public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findAll();
    void deleteById(long ID);


}
