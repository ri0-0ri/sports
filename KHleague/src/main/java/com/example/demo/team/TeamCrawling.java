package com.example.demo.team;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeamCrawling {
	public static void main(String[] args) {
		SpringApplication.run(TeamCrawling.class, args);
		
		System.setProperty("webdriver.edge.driver", "C:\\edgedriver_win64\\edgedriver.exe");
		String url = "https://www.fcseoul.com/club/clubPlayerIntroductionList/FW";
		WebDriver driver = new EdgeDriver();
		driver.get(url);
	}
}
