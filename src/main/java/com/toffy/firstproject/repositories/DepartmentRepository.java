package com.toffy.firstproject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.toffy.firstproject.models.Department;
import com.toffy.firstproject.models.Employee;


public interface DepartmentRepository extends CrudRepository<Department, Long> {
    List<Department> findAll();
    Optional<Department> findByName(String name);
    void deleteById(long ID);


}
