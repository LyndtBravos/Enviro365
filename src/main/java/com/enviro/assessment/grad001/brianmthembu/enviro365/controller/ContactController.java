package com.enviro.assessment.grad001.brianmthembu.enviro365.controller;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Contact;
import com.enviro.assessment.grad001.brianmthembu.enviro365.repo.ContactRepo;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping("api/contact")
public class ContactController
{
	private final ContactRepo contactRepository;

	@GetMapping("/getAllContacts")
	public List<Contact> getContacts() {
		return contactRepository.findAll();
	}

	@GetMapping("/getContactByInvestorId/{id}")
	public List<Contact> getContacts(@PathVariable int id) {
		return contactRepository.findByInvestorID(id);
	}

	@PostMapping("/createContact")
	public ResponseEntity<?> createContact(@RequestBody Contact contact) {
		return contactRepository.insert(contact);
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		return contactRepository.delete(id);
	}

}
