package com.enviro.assessment.grad001.brianmthembu.enviro365.controller;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.WithdrawalNotice;
import com.enviro.assessment.grad001.brianmthembu.enviro365.service.WithdrawalNoticeService;
import lombok.AllArgsConstructor;
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
@RequestMapping("/api/notice")
public class WithdrawalNoticeController
{

	private final WithdrawalNoticeService service;

	public WithdrawalNoticeController(WithdrawalNoticeService service) {
		this.service = service;
	}

	@GetMapping("/getNotices")
	public ResponseEntity<List<WithdrawalNotice>> getNotices() {
		List<WithdrawalNotice> notices = service.getNotices();
		return new ResponseEntity<>(notices, HttpStatus.OK);
	}

	@GetMapping("/getNotice/{id}")
	public List<WithdrawalNotice> getNotices(@PathVariable int id) {
		return service.getNoticesByInvestorId(id);
	}

	@GetMapping("/readNoticesFromFile")
	public ResponseEntity<String> readNotices() {
		return service.readWithdrawalNotices();
	}

	@PostMapping("/writeNotices")
	public ResponseEntity<String> writeNotices() {
		String stringReturned = service.writeWithdrawalNotices(service.getNotices());
		return new ResponseEntity<>(stringReturned, stringReturned.contains("Exception") ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}

	@PostMapping("/createNotice")
	public ResponseEntity<?> createNotice(@RequestBody WithdrawalNotice notice) {
		String stringReturned = service.createNotice(notice);
		return new ResponseEntity<>(stringReturned, stringReturned.contains("success") ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deleteNotice/{id}")
	public ResponseEntity<String> deleteNotice(@PathVariable int id) {
		String stringReturned = service.deleteNotice(id);
		return new ResponseEntity<>(stringReturned, stringReturned.contains("success") ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
}
