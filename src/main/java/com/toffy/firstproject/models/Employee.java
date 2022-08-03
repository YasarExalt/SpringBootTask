package com.toffy.firstproject.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    private String role;
    private int age;
    private int phoneNumber;
    private int baseSalary;
    @Transient
    private int currentSalary;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
 
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
        name = "employees_departments", 
        joinColumns = @JoinColumn(name = "employee_id"), 
        inverseJoinColumns = @JoinColumn(name = "deparment_id")
    )

    @JsonIgnoreProperties("employees")
	private List<Department> departments;
    
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="address_id")
    private Address address;
    
//    @OneToMany(mappedBy="employee", fetch = FetchType.LAZY)
//    private List<Department> managedDepartments;
    
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id")
	private Department department;

	public Department getDepartment() {
		return department;
	}




	public void setDepartment(Department department) {
		this.department = department;
	}




	public Employee() {
	}

	


	public Employee(String name) {
		this.name = name;
	}






	public Employee(String name, String gender) {
		this.name = name;
		this.gender = gender;
	}




	public Employee(String name, String gender, String role) {
		this.name = name;
		this.gender = gender;
		this.role = role;
	}




	public Employee(String name, String gender, String role, int age) {
		this.name = name;
		this.gender = gender;
		this.role = role;
		this.age = age;
	}




	public Employee(String name, String gender, String role, int age, int phoneNumber) {
		this.name = name;
		this.gender = gender;
		this.role = role;
		this.age = age;
		this.phoneNumber = phoneNumber;
	}









	public Employee(String name, String gender, String role, int age, int phoneNumber, int baseSalary) {
		this.name = name;
		this.gender = gender;
		this.role = role;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.baseSalary = baseSalary;
	}

	public Employee(String name, String gender, String role, int age, int phoneNumber, int baseSalary,
			LocalDate hireDate) {
		this.name = name;
		this.gender = gender;
		this.role = role;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.baseSalary = baseSalary;
		this.hireDate = hireDate;
	}


	public Employee(String name, String gender, String role, int age, int phoneNumber, int baseSalary,
			LocalDate hireDate, List<Department> departments) {
		this.name = name;
		this.gender = gender;
		this.role = role;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.baseSalary = baseSalary;
		this.hireDate = hireDate;
		this.departments = departments;
	}




	public Employee(String name, String gender, String role, int age, int phoneNumber, int baseSalary,
			LocalDate hireDate, List<Department> departments, Address address) {
		this.name = name;
		this.gender = gender;
		this.role = role;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.baseSalary = baseSalary;
		this.hireDate = hireDate;
		this.departments = departments;
		this.address = address;
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




	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public String getRole() {
		return role;
	}




	public void setRole(String role) {
		this.role = role;
	}




	public int getAge() {
		return age;
	}




	public void setAge(int age) {
		this.age = age;
	}




	public int getPhoneNumber() {
		return phoneNumber;
	}




	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public LocalDate getHireDate() {
		return hireDate;
	}




	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}


	public int getBaseSalary() {
		return baseSalary;
	}




	public void setBaseSalary(int baseSalary) {
		this.baseSalary = baseSalary;
	}

	   public int getCurrentSalary() {
		   if (currentSalary == 0) {
			   setCurrentSalary(currentSalary);
		   }
		   return currentSalary;
		}




		public void setCurrentSalary(int currentSalary) {
    		LocalDate localDate = LocalDate.now();
    		LocalDate hireDate = this.getHireDate();
    		System.out.print(this.getHireDate());
    		System.out.print(hireDate);
    		if (hireDate != null) {
    			Period period = Period.between(hireDate, localDate);
        		this.currentSalary = this.baseSalary + 200 * (period.getYears());
    		}else {
    			this.currentSalary = this.baseSalary;
    		}
		}




	public List<Department> getDepartments() {
		return departments;
	}




	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}




	public Address getAddress() {
		return address;
	}




	public void setAddress(Address address) {
		this.address = address;
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