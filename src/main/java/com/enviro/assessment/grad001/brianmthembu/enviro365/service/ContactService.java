package com.enviro.assessment.grad001.brianmthembu.enviro365.service;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Contact;
import com.enviro.assessment.grad001.brianmthembu.enviro365.repo.ContactRepo;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService
{
	@Autowired
	ContactRepo contactRepository;

	public List<Contact> getContacts() {
		return contactRepository.findAll();

	}

	public List<Contact> getContactsByInvestorId(int id) {
		return contactRepository.findByInvestorID(id);
	}

	public String createContact(Contact contact) {
		return contactRepository.insert(contact);
	}

	public String deleteById(int id) {
		return contactRepository.delete(id);
	}

}
