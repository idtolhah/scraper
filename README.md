# scraper
Tokopedia e-commerce top 100 handphone category products scraper

The tech stacks:
- Java 11
- Maven 3.6.3
- Spring Boot 2.5.3
- Selenium
- Jackson Dataformat Csv

The concept behind this utility
- Using java to scrape products data with selenium webdriver library
- It depends so much on the web browser we are using
- The version of our web browser (eg: chrome) must match to the webdriver version
- Selenium is basically an utility to do an automated recorded testing through web browser mechanism
- By using selenium library, we can scrape data on background just like browsing via web browser
- We also use jackson dataformat to import scraped data into csv file

- First, when this program is running, it begins to scrape products data using selenium webdriver by looking at the xpath of the html element of the website to get Name of Product, Description, Image Link, Price, Rating(out of 5 stars), Name of store or merchant and the product's link values.
- For each element, it has to go through the link to scrape the description value.
- After scraping proccess, it then use CsvMapper form jackson dataformat csv library to try writing scraped data values into the csv file.
- If the csv writing proccess is success, then the csv file will be produced and saved to local directory.

