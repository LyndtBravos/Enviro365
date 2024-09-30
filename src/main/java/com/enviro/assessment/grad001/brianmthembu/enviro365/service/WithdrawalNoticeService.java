package com.enviro.assessment.grad001.brianmthembu.enviro365.service;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.WithdrawalNotice;
import com.enviro.assessment.grad001.brianmthembu.enviro365.repo.WithdrawalNoticeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class WithdrawalNoticeService
{
	@Autowired
	private WithdrawalNoticeRepo repo;

	public List<WithdrawalNotice> getNotices() {
		return repo.findAll();
	}

	public List<WithdrawalNotice> getNoticesByInvestorId(int id) {
		return repo.findByInvestorId(id);
	}

	public ResponseEntity<String> readWithdrawalNotices() {
		String path = Paths.get("").toAbsolutePath().toString();
		String newPath = path.concat("\\src\\main\\resources\\Withdrawal Notice\\file.csv");
		String result = "";

		try{
			List<String> list = Files.readAllLines(Paths.get(newPath));
			for (String word : list)
				result = result.concat(word + "\n");
		}catch(IOException e) {
			result = "Exception caught: " + e.getMessage();
		}

		return result.contains("Exception") ? new ResponseEntity<>(result, HttpStatus.NOT_FOUND) : new ResponseEntity<>(result, HttpStatus.OK);
	}

	public String writeSingleWithdrawalNotice(WithdrawalNotice notice) {

		if(notice == null){
			return "Notice is empty. Fill it first and then try again";
		}

		String path = Paths.get("").toAbsolutePath().toString();
		String newPath = path.concat("\\src\\main\\resources\\Withdrawal Notice\\file.csv");
		String result = "The file with the requested info has been saved successfully.";

		try(FileWriter writer = new FileWriter(newPath, true))
		{
			writer.write("\nWithdrawal Notice Info \n");
			writer.write("ID: " + notice.getInvestorID() + "\n");

			writer.write("\nProduct: " + notice.getProductID());
			writer.write("\nWithdrawal Amount: " + notice.getWithdrawalAmount());
			writer.write("\nWithdrawal Date: " + notice.getWithdrawalDate());
			writer.write("\nStatement Date: " + notice.getStatementDate());
			writer.write("\n\n");


			writer.flush();

		}catch(IOException e){
			result = "Exception caught: " + e.getMessage();
		}

		return result;
	}

	public String writeWithdrawalNotices(List<WithdrawalNotice> notice) {
		String result = "";
		for (var i : notice) {
			result = writeSingleWithdrawalNotice(i);
		}

		return result;

	}

	public String createNotice(WithdrawalNotice notice) {
		return repo.insert(notice);
	}

	public String deleteNotice(int id) {
		return repo.delete(id);
	}

}
