package com.enviro.assessment.grad001.brianmthembu.enviro365;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Investor;
import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Product;
import com.enviro.assessment.grad001.brianmthembu.enviro365.model.ProductType;
import com.enviro.assessment.grad001.brianmthembu.enviro365.model.WithdrawalNotice;
import com.enviro.assessment.grad001.brianmthembu.enviro365.repo.InvestorRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Service {

	static InvestorRepo investorRepo = new InvestorRepo();

	public static ResponseEntity<?> readWithdrawalNotice() {
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

	public static String writeWithdrawalNotice(WithdrawalNotice notice) {

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
			result = e.getMessage();
		}

		return result;
	}

	public static ResponseEntity<?> writeWithdrawalNotice(List<WithdrawalNotice> notice) {
		String result = "";
		for (var i : notice) {
			result = writeWithdrawalNotice(i);
		}

		if(result.contains("saved successfully."))
			return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
		else return new ResponseEntity<>(result, HttpStatus.SERVICE_UNAVAILABLE);

	}

	public static boolean validateRetirementProductType(Product product) {
		Investor investor = investorRepo.findAll()
				.stream()
				.filter(i -> product.getInvestorID() == i.getId())
				.findFirst()
				.orElse(null);

		if(investor == null)
			return false;

		if(product.getProductType().contains("Retirement") && investor.getAge() > 65)
			return true;
		else
			return !product.getProductType().contains("Retirement");
	}

	public static boolean validateProductType(Product product)
	{
		String type = product.getProductType();

		boolean var = false;
		for(var i : ProductType.values())
			if (i.getDisplayName().contains(type)) {
				var = true;
				break;
			}

		if(!var || type.contains("Retirement"))
			var = validateRetirementProductType(product);

		return var;
	}
}