package com.toffy.firstproject.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toffy.firstproject.models.Department;
import com.toffy.firstproject.models.Employee;
import com.toffy.firstproject.services.DepartmentService;
import com.toffy.firstproject.services.EmployeeService;

@RestController
@CrossOrigin("*")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;
	@PostMapping(value = "saveEmployee")
	public String addNewemployee(@RequestBody Employee employment) {
		System.out.println("At Controller Add");
		employeeService.createEmployee(employment);
		return "Added Successfully";
	}
	@PostMapping(value="/createEmployee")
	public String createEmployee(@Valid@RequestParam("departmentid") Long id,@RequestBody Employee emp) {
		Department dep = departmentService.findDepartment(id);
		ArrayList<Department> departmentList = new ArrayList<Department>();
		departmentList.add(dep);
		emp.setDepartments(departmentList);
		

		employeeService.createEmployee(emp);
		return "Employee was Added Successfully";
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
	
	@GetMapping(value = "findAllEmployee", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@PostMapping(value="/addDepartment/{id}")
	public String addDepartment(@Valid@RequestParam("departmentid") Long id,@PathVariable("id") Long employeeId) {
		Department dep = departmentService.findDepartment(id);
		Employee employee = employeeService.findEmployee(employeeId);
		employee.getDepartments().add(dep);
		employeeService.createEmployee(employee);
		return "Department was Added Successfully";
	}
}
