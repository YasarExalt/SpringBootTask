package com.toffy.firstproject.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.toffy.firstproject.models.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    List<Employee> findAll();
    void deleteById(long ID);
	List<Employee> findByGender(String gender);
	List<Employee> findByRole(String role);
}
