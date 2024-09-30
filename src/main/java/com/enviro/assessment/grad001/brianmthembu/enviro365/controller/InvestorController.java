package com.enviro.assessment.grad001.brianmthembu.enviro365.controller;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Investor;
import com.enviro.assessment.grad001.brianmthembu.enviro365.service.InvestorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/investor")
public class InvestorController
{
	private final InvestorService service;

	public InvestorController(InvestorService service){
		this.service = service;
	}

	@GetMapping("/sayHello/{name}")
	public String sayHello(@PathVariable String name){
		return "Hello, " + name;
	}

	@GetMapping("/getInvestors")
	public ResponseEntity<List<Investor>> getInvestors() {
		List<Investor> investors = service.getInvestors();
		return new ResponseEntity<>(investors, HttpStatus.OK);
	}

	@GetMapping("/getInvestorsByName/{name}")
	public Investor getInvestors(@PathVariable String name) {
		return service.getInvestorsByName(name);
	}

	@PostMapping("/addMoney/{name}/{amount}")
	public ResponseEntity<?> increaseFunds(@PathVariable String name, @PathVariable double amount) {
		return service.increaseFunds(name, amount);
	}

	@PostMapping("/withdrawMoney/{name}/{amount}")
	public ResponseEntity<String> withdrawFunds(@PathVariable String name, @PathVariable double amount) {
		return service.withdrawFunds(name, amount);
	}

	@PostMapping("/createInvestor")
	public ResponseEntity<String> createInvestor(@RequestBody Investor investor) {
		return service.createInvestor(investor);
	}
}