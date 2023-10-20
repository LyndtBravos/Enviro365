package com.enviro.assessment.grad001.brianmthembu.enviro365.controller;

import com.enviro.assessment.grad001.brianmthembu.enviro365.Service;
import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Investor;
import com.enviro.assessment.grad001.brianmthembu.enviro365.repo.InvestorRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/investor")
@AllArgsConstructor
public class InvestorController
{
	private final InvestorRepo investorRepository;

	@GetMapping("/sayHello/{name}")
	public String sayHello(@PathVariable String name){
		return "Hello, " + name;
	}

	@GetMapping("/getInvestors")
	public Iterable<Investor> getInvestors() {
		return investorRepository.findAll();
	}

	@GetMapping("/getInvestorsByName/{name}")
	public Investor getInvestors(@PathVariable String name) {
		return investorRepository.findByName(name);
	}

	@PostMapping("/addMoney/{name}/{amount}")
	public ResponseEntity<?> increaseFunds(@PathVariable String name, @PathVariable double amount) {
		return investorRepository.increaseFunds(name, amount);
	}

	@PostMapping("/withdrawMoney/{name}/{amount}")
	public ResponseEntity<?> withdrawFunds(@PathVariable String name, @PathVariable double amount) {
		return investorRepository.withdrawFunds(name, amount);
	}

	@PostMapping("/createInvestor")
	public ResponseEntity<?> createInvestor(@RequestBody Investor investor) {
		return investorRepository.insert(investor);
	}
}