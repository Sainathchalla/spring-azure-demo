package com.IPL.MajorProject.DataLoaderPkg;

import com.IPL.MajorProject.EntityPkg.MatchEntity;
import com.IPL.MajorProject.RepositoryPkg.MatchRepository;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCsvData();
    }

    private void loadCsvData() throws Exception {
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);

        CsvParser parser = new CsvParser(settings);
        try (Reader reader = new InputStreamReader(new ClassPathResource("matches.csv").getInputStream())) {
            List<String[]> records = parser.parseAll(reader);
            for (String[] record : records) {
                MatchEntity match = new MatchEntity();
                match.setSeason(Long.valueOf(record[1]));
                match.setCity(record[2]);
                match.setDate(record[3]);
                match.setMatch_type(record[4]);
                match.setPlayer_of_match(record[5]);
                match.setVenue(record[6]);
                match.setTeam1(record[7]);
                match.setTeam2(record[8]);
                match.setToss_winner(record[9]);
                match.setToss_decision(record[10]);
                match.setWinner(record[11]);
                match.setResult(record[12]);

                match.setResult_margin(parseLong(record[13]));
                match.setTarget_runs(parseLong(record[14]));
                match.setTarget_overs(parseDouble(record[15]));

                match.setSuper_over(record[16]);
                match.setMethod(record[17]);
                match.setUmpire1(record[18]);
                match.setUmpire2(record[19]);

                matchRepository.save(match);
            }
        }
    }

    private Long parseLong(String value) {
        if (value == null || value.isEmpty() || value.equalsIgnoreCase("NA")) {
            return 0L; // or handle the default value as per your requirement
        }
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            // Handle the exception as needed
            return null; // or handle the default value
        }
    }

    private Double parseDouble(String value) {
        if (value == null || value.isEmpty() || value.equalsIgnoreCase("NA")) {
            return 0.0; // or handle the default value as per your requirement
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            // Handle the exception as needed
            return null; // or handle the default value
        }
    }
}
