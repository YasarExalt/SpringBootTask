package com.toffy.firstproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.toffy.firstproject.models.Address;
import com.toffy.firstproject.repositories.AddressRepository;

@Service
public class AddressService {

	private final AddressRepository addressRepository;

	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
    public List<Address> getAllAddresss() {
        return addressRepository.findAll();
    }
    // creates a Address
    public Address createAddress(Address t) {
        return addressRepository.save(t);
    }
    // retrieves a Address
    public Address findAddress(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(optionalAddress.isPresent()) {
            return optionalAddress.get();
        } else {
            return null;
        }
    }
    public void updateAddress(Address address) {
    	Address editAddress = addressRepository.findById(address.getId()).orElse(null);
    	assert editAddress !=null;
    	editAddress.setLocation(address.getLocation());
    	addressRepository.save(editAddress);
    	
    }
    public void deleteAddress(Long id) {
    	Address address = findAddress(id);
    	if(address!=null) {
    		addressRepository.delete(address);
    	}
    
    }


}
