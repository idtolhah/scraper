package com.tlhh.scraper;

import java.util.logging.Level;

import com.tlhh.scraper.errors.FailException;
import com.tlhh.scraper.service.ScrapperService;
import com.tlhh.scraper.utils.Scrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScraperApplication.class, args);
		java.util.logging.Logger.getLogger("org.openqa").setLevel(Level.OFF);
        System.out.println("Scrapping " + Scrapper.COUNT + " " + Scrapper.CATEGORY + " products...");
        ScrapperService service = new ScrapperService();
        try {
            String csv = service.downloadProductListCsv();
            System.out.println("Scrapping is successfully and will be saved to " + csv);
        } catch (FailException e) {
            System.err.println("Failed. Please try again.");
        }
	}

}
