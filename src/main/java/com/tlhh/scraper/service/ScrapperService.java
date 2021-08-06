package com.tlhh.scraper.service;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.annotations.VisibleForTesting;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.tlhh.scraper.domain.Product;
import com.tlhh.scraper.errors.FailException;
import com.tlhh.scraper.utils.Scrapper;

public class ScrapperService {

    private static final String UNDERSCORE = "_";
    private static final String PRODUCT = "Product";
    private static final String CSV_EXT = ".csv";

    private Scrapper scrapper;

    public ScrapperService() {
        scrapper = new Scrapper();
    }

    @VisibleForTesting
    ScrapperService(Scrapper scrapper) {
        this.scrapper = scrapper;
    }

    public String processTheCsv()
            throws FailException {
        String filename = PRODUCT + UNDERSCORE + Scrapper.CATEGORY + UNDERSCORE + System.currentTimeMillis() + CSV_EXT;
        List<Product> products = scrapper.scrapeProducts();

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.enable(Feature.IGNORE_UNKNOWN);
        csvMapper.addMixIn(Product.class, Product.ProductFormat.class);
        CsvSchema schema = csvMapper.schemaFor(Product.class).withHeader();

        try {
            File file = new File(filename);
            csvMapper.writer(schema).writeValue(file, products);
            return filename;
        } catch (IOException | RuntimeException e) {
            throw new FailException(e.getMessage());
        }
    }
}
