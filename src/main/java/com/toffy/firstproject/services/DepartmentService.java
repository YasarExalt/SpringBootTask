package com.toffy.firstproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.toffy.firstproject.models.Department;
import com.toffy.firstproject.repositories.DepartmentRepository;

@Service
public class DepartmentService {
	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    // creates a Department
    public Department createDepartment(Department t) {
        return departmentRepository.save(t);
    }
    // retrieves a Department
    public Department findDepartment(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if(optionalDepartment.isPresent()) {
            return optionalDepartment.get();
        } else {
            return null;
        }
    }
 // retrieves a Department
    public Department findDepartmentByName(String name) {
        Optional<Department> optionalDepartment = departmentRepository.findByName(name);
        if(optionalDepartment.isPresent()) {
            return optionalDepartment.get();
        } else {
            return null;
        }
    }
    public void updateDepartment(Department department) {
    	Department editDepartment = departmentRepository.findById(department.getId()).orElse(null);
    	assert editDepartment !=null;
    	editDepartment.setName(department.getName());
    	editDepartment.setEmployees(department.getEmployees());
    	departmentRepository.save(editDepartment);
    	
    }
    public void deleteDepartment(Long id) {
    	Department department = findDepartment(id);
    	if(department!=null) {
    		departmentRepository.delete(department);
    	}
    
    }
}
