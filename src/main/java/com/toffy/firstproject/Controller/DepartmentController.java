package com.toffy.firstproject.Controller;

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

import com.toffy.firstproject.models.Department;
import com.toffy.firstproject.models.Employee;
import com.toffy.firstproject.services.DepartmentService;

@RestController
@CrossOrigin("*")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	@PostMapping(value = "saveDepartment")
	public String addNewemployee(@RequestBody Department department) {
		System.out.println("At Controller Add");
		departmentService.createDepartment(department);
		return "Added Successfully";
	}
	@PutMapping(value = "updateDepartment")
	public String updateemployee(@RequestBody Department department) {
		departmentService.updateDepartment(department);
		return "updated Successfully";
	}
	@DeleteMapping(value = "deleteDepartment")
	public String deleteemployee(@RequestParam Long id) {
		departmentService.deleteDepartment(id);
		return "employee Deleted";
	}
	
	@GetMapping(value = "findAllDepartment")
	public List<Department> findAllemployee(){
		return departmentService.getAllDepartments();
	}
	
	@GetMapping(value = "findDepartmentEmployees/{department}")
	public List<Employee> findDepartmentEmployees(@PathVariable("department") String department){
		Department dep = departmentService.findDepartmentByName(department);
		return dep.getEmployees();
	}


}