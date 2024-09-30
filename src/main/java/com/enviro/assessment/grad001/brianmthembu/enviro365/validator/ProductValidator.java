package com.enviro.assessment.grad001.brianmthembu.enviro365.validator;

import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Investor;
import com.enviro.assessment.grad001.brianmthembu.enviro365.model.Product;
import com.enviro.assessment.grad001.brianmthembu.enviro365.model.ProductType;
import com.enviro.assessment.grad001.brianmthembu.enviro365.repo.InvestorRepo;
import com.enviro.assessment.grad001.brianmthembu.enviro365.service.InvestorService;

public class ProductValidator
{

	public static boolean validateRetirementProductType(Product product) {
		Investor investor = new InvestorService(new InvestorRepo()).searchForInvestorByIdFromList(product.getInvestorID());

		if(investor == null)
			return true;

		if(product.getProductType().equalsIgnoreCase("Retirement") && investor.getAge() >= 65)
			return false;
		else if(!product.getProductType().equalsIgnoreCase("Retirement"))
			return false;

		return product.getProductType().contains("Retirement");
	}

	public boolean validateProductType(Product product)
	{
		String type = product.getProductType();

		boolean var = false;
		for(var i : ProductType.values())
			if (i.getDisplayName().equalsIgnoreCase(type)) {
				var = true;
				break;
			}

		if(var)
			var = validateRetirementProductType(product);
		else var = true;

		return var;
	}
}
