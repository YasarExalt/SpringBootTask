package com.toffy.firstproject.Controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toffy.firstproject.models.Employee;
import com.toffy.firstproject.services.EmployeeService;

@Controller
public class PaginationSortingEmployee {
	
	private final EmployeeService employmentService;
    public PaginationSortingEmployee(EmployeeService employmentService) {
        this.employmentService = employmentService;
    }

	@RequestMapping("/pages/{pageNumber}")
	public String getNinjasPerPage(Model model, @PathVariable("pageNumber") int pageNumber) {
	    Page<Employee> employees = employmentService.employeesPerPage(pageNumber - 1);
	    // total number of pages that we have
	    int totalPages = employees.getTotalPages();
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("employees", employees);
	    return "employees.jsp";
	}

}
