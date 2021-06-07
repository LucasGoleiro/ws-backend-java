package com.backend.backend;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	
	/*@GetMapping("/")
	public String hello() {
		return databaseConnectionTest();
	}
	
	public String databaseConnectionTest() {
		String urlConnection = "jdbc:mysql://localhost:3306/backend";
		
		try {
			Connection conn = DriverManager.getConnection(urlConnection, "root", "root");
			return "Deu certo a conexão !!!!!!!!!!";
		} catch (SQLException e) {
			return "Deu errado";
		}
	} */
}
