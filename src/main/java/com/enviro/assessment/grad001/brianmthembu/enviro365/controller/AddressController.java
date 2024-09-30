package com.enviro.assessment.grad001.brianmthembu.enviro365.controller;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Address;
import com.enviro.assessment.grad001.brianmthembu.enviro365.service.AddressService;
import org.springframework.http.HttpStatus;
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
	private final AddressService addressService;

	public AddressController(AddressService addressService)
	{
		this.addressService = addressService;
	}

	@GetMapping("/getAllAddresses")
	public ResponseEntity<List<Address>> getAllAddresses() {
		List<Address> addresses = addressService.getAllAddresses();
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}

	@GetMapping("/getAddressByInvestorId/{id}")
	public ResponseEntity<List<Address>> getAddresses(@PathVariable int id) {
		List<Address> addressList = addressService.getAddressesByInvestorId(id);
		return new ResponseEntity<>(addressList, HttpStatus.OK);
	}

	@PostMapping("/createAddress")
	public ResponseEntity<?> createAddress(@RequestBody Address address) {
		return addressService.createAddress(address);
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id) {
		return addressService.deleteById(id);
	}
}
