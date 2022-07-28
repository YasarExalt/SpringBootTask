package com.toffy.firstproject.Controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toffy.firstproject.models.Employee;
import com.toffy.firstproject.services.EmployeeService;

@RestController
@CrossOrigin("*")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	@PostMapping(value = "saveEmployee")
	public String addNewemployee(@RequestBody Employee employment) {
		System.out.println("At Controller Add");
		employeeService.createEmployee(employment);
		return "Added Successfully";
	}
	@PutMapping(value = "updateEmployee")
	public String updateemployee(@RequestBody Employee employment) {
		employeeService.updateEmployee(employment);
		return "updated Successfully";
	}
	@DeleteMapping(value = "deleteEmployee")
	public String deleteemployee(@RequestParam Long id) {
		employeeService.deleteEmployee(id);
		return "employee Deleted";
	}
	
	@GetMapping(value = "findAllEmployee")
	public List<Employee> findAllemployee(){
		return employeeService.getAllEmployees();
	}

	@GetMapping(value = "findEmployeesByGender/{gender}")
	public List<Employee> findEmployeeByGender(@PathVariable("gender") String gender){
		return employeeService.findEmployeesByGender(gender);
	}

	@GetMapping(value = "findEmployeesByRole/{role}")
	public List<Employee> findEmployeeByRole(@PathVariable("role") String role){
		return employeeService.findEmployeesByRole(role);
	}

    @GetMapping("/users-current-salary")
    public int getUserCurrentSalary(@RequestParam Long id){
    	Employee employee = employeeService.findEmployee(id);
    	if(employee!=null) {
    		int baserSalary = employee.getBaseSalary();
    		LocalDate localDate = LocalDate.now();
    		LocalDate hireDate = employee.getHireDate();

            Period period = Period.between(hireDate, localDate);
            System.out.print(period.getYears() + " years,");
            
    		if(period.getYears() >= 1 ) {
    			int currentSalary = baserSalary + 200* (period.getYears());
    			return currentSalary;
    		}
    		return baserSalary;
    	}
        return 0;
    }

}
