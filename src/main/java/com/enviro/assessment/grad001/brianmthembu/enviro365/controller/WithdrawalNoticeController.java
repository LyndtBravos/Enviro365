package com.enviro.assessment.grad001.brianmthembu.enviro365.controller;

import com.enviro.assessment.grad001.brianmthembu.enviro365.Service;
import com.enviro.assessment.grad001.brianmthembu.enviro365.model.WithdrawalNotice;
import com.enviro.assessment.grad001.brianmthembu.enviro365.repo.WithdrawalNoticeRepo;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
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
@AllArgsConstructor
public class WithdrawalNoticeController
{

	private final WithdrawalNoticeRepo noticeRepository;

	@GetMapping("/getNotices")
	public List<WithdrawalNotice> getNotices() {
		return noticeRepository.findAll();
	}

	@GetMapping("/getNotice/{id}")
	public List<WithdrawalNotice> getNotices(@PathVariable int id) {
		return noticeRepository.findByInvestorId(id);
	}

	@GetMapping("/readNoticesFromFile")
	public ResponseEntity<?> readNotices() {
		return Service.readWithdrawalNotice();
	}

	@PostMapping("/writeNotices")
	public ResponseEntity<?> writeNotices() {
		return Service.writeWithdrawalNotice(noticeRepository.findAll());
	}

	@PostMapping("/createNotice")
	public ResponseEntity<?> createNotices(@RequestBody WithdrawalNotice notice) {
		return noticeRepository.insert(notice);
	}

	@DeleteMapping("/deleteNotice/{id}")
	public ResponseEntity<?> deleteNotice(@PathVariable int id) {
		return noticeRepository.delete(id);
	}
}
