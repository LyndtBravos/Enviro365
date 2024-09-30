package com.enviro.assessment.grad001.brianmthembu.enviro365.controller;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Contact;
import com.enviro.assessment.grad001.brianmthembu.enviro365.service.ContactService;
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
@RequestMapping("api/contact")
public class ContactController
{
	private final ContactService service;

	public ContactController(ContactService service)
	{
		this.service = service;
	}

	@GetMapping("/getAllContacts")
	public ResponseEntity<List<Contact>> getContacts() {
		List<Contact> contacts = service.getContacts();
		return new ResponseEntity<>(contacts, HttpStatus.OK);
	}

	@GetMapping("/getContactByInvestorId/{id}")
	public ResponseEntity<List<Contact>> getContacts(@PathVariable int id) {
		return new ResponseEntity<>(service.getContactsByInvestorId(id), HttpStatus.OK);
	}

	@PostMapping("/createContact")
	public ResponseEntity<?> createContact(@RequestBody Contact contact) {
		String stringReturned = service.createContact(contact);
		return new ResponseEntity<>(stringReturned, stringReturned.contains("success") ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}


	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		String stringReturned = service.deleteById(id);
		return new ResponseEntity<>(stringReturned, stringReturned.contains("success") ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

}
