package com.toffy.firstproject.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.toffy.firstproject.dto.SalaryDTO;
import com.toffy.firstproject.models.Employee;
import com.toffy.firstproject.repositories.EmployeeRepository;


@Service
@Transactional
public class EmployeeService {
	
    @Autowired
    private ModelMapper modelMapper;

    @Autowired 
    EmployeeRepository employeeRepo;
    private static final int PAGE_SIZE = 2;
    public Page<Employee> employeesPerPage(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE, Sort.Direction.ASC, "name");
        Page<Employee> employees = employeeRepo.findAll(pageRequest);
        return employeeRepo.findAll(pageRequest);
    }
    
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }
    
    public List<Employee> findEmployeesByGender(String gender) {
        return employeeRepo.findByGender(gender);
    }

    public List<Employee> findEmployeesByRole(String role) {
        return employeeRepo.findByRole(role);
    }

    // creates a Employee
    public Employee createEmployee(Employee t) {
        return employeeRepo.save(t);
    }
    // retrieves a Employee
    public Employee findEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if(optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            return null;
        }
    }
    public void updateEmployee(Employee employee) {
    	Employee editEmployee = employeeRepo.findById(employee.getId()).orElse(null);
    	assert editEmployee !=null;
    	editEmployee.setName(employee.getName());
    	editEmployee.setGender(employee.getGender());
    	editEmployee.setRole(employee.getRole());
    	editEmployee.setAge(employee.getAge());
    	editEmployee.setPhoneNumber(employee.getPhoneNumber());
    	editEmployee.setBaseSalary(employee.getBaseSalary());
    	editEmployee.setHireDate(employee.getHireDate());
    	editEmployee.setDepartments(employee.getDepartments());
    	editEmployee.setAddress(employee.getAddress());
    	employeeRepo.save(editEmployee);
    	
    }
    public void deleteEmployee(Long id) {
    	Employee employee = findEmployee(id);
    	if(employee!=null) {
    		employeeRepo.delete(employee);
    	}
    
    }
    
    private SalaryDTO convertEntityToDto(Employee employee){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        SalaryDTO salaryDTO = new SalaryDTO();
        salaryDTO = modelMapper.map(employee, SalaryDTO.class);
        return salaryDTO;
    }


}
