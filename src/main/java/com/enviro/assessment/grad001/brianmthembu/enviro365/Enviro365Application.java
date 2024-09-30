package com.enviro.assessment.grad001.brianmthembu.enviro365;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class Enviro365Application
{

	public static void main(String[] args)
	{
		SpringApplication.run(Enviro365Application.class, args);
	}

}
