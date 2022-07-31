package com.toffy.firstproject.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "employees_departments", 
        joinColumns = @JoinColumn(name = "deparment_id"), 
        inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees;

    @OneToOne(mappedBy="department", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Employee manager;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="employee_id")
//    private Employee manager;

	public Department() {
	}

	
	

	public Employee getManager() {
		return manager;
	}




	public void setManager(Employee manager) {
		this.manager = manager;
	}




	public Department(String name) {
		this.name = name;
	}
	
	public Department(String name, List<Employee> employees) {
		this.name = name;
		this.employees = employees;
	}


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public List<Employee> getEmployees() {
		return employees;
	}



	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}


	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date(); 
    }
	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
}