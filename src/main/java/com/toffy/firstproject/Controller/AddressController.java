package com.toffy.firstproject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toffy.firstproject.models.Address;
import com.toffy.firstproject.services.AddressService;


@RestController
@CrossOrigin("*")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	@PostMapping(value = "saveAddress")
	public String addNewAddress(@RequestBody Address address) {
		System.out.println("At Controller Add");
		addressService.createAddress(address);
		return "Added Successfully";
	}
	@PutMapping(value = "updateAddress")
	public String updateAddress(@RequestBody Address address) {
		addressService.updateAddress(address);
		return "updated Successfully";
	}
	@DeleteMapping(value = "deleteAddress")
	public String deleteAddress(@RequestParam Long id) {
		addressService.deleteAddress(id);
		return "Address Deleted";
	}
	
	@GetMapping(value = "findAllAddress")
	public List<Address> findAllAddress(){
		return addressService.getAllAddresss();
	}

}
