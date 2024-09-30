package com.enviro.assessment.grad001.brianmthembu.enviro365.service;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Address;
import com.enviro.assessment.grad001.brianmthembu.enviro365.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService
{
	@Autowired
	private AddressRepo addressRepository;

	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

	public List<Address> getAddressesByInvestorId(int id) {
		return addressRepository.findByInvestorID(id);
	}

	public ResponseEntity<String> createAddress(Address address) {
		return addressRepository.insert(address);
	}

	public ResponseEntity<String> deleteById(int id) {
		return addressRepository.delete(id);
	}
}
