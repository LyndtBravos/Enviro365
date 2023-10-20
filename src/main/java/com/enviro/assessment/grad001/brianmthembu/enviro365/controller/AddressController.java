package com.enviro.assessment.grad001.brianmthembu.enviro365.controller;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Address;
import com.enviro.assessment.grad001.brianmthembu.enviro365.repo.AddressRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController
{
	private final AddressRepo addressRepository;

	public AddressController(AddressRepo addressRepository)
	{
		this.addressRepository = addressRepository;
	}

	@GetMapping("/getAllAddresses")
	public List<Address> getAddresses() {
		return addressRepository.findAll();
	}

	@GetMapping("/getAddressByInvestorId/{id}")
	public List<Address> getAddresses(@PathVariable int id) {
		return addressRepository.findByInvestorID(id);
	}

	@PostMapping("/createAddress")
	public ResponseEntity<?> createAddress(@RequestBody Address address) {
		return addressRepository.insert(address);
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		return addressRepository.delete(id);
	}
}
