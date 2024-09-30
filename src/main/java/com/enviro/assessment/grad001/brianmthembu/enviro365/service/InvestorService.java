package com.enviro.assessment.grad001.brianmthembu.enviro365.service;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Investor;
import com.enviro.assessment.grad001.brianmthembu.enviro365.repo.InvestorRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorService
{
	@Autowired
	private final InvestorRepo repo;

	public InvestorService(InvestorRepo repo) {
		this.repo = repo;
	}

	public List<Investor> getInvestors() {
		return repo.findAll();
	}

	public Investor getInvestorsByName(String name) {
		return repo.findByName(name);
	}

	public ResponseEntity<String> increaseFunds(String name, double amount) {
		return repo.increaseFunds(name, amount);
	}

	public ResponseEntity<String> withdrawFunds(String name, double amount) {
		return repo.withdrawFunds(name, amount);
	}

	public ResponseEntity<String> createInvestor(Investor investor) {
		return repo.insert(investor);
	}

	public Investor searchForInvestorByIdFromList(int investorId) {
		return getInvestors()
				.stream()
				.filter(i -> investorId == i.getId())
				.findFirst()
				.orElse(null);
	}
}
