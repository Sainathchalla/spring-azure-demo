package com.IPL.MajorProject.ControllerPkg;

import com.IPL.MajorProject.EntityPkg.MatchEntity;
import com.IPL.MajorProject.ServicePkg.MatchServices;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
public class MatchController {

    @Autowired
    MatchServices matchService;

    @CrossOrigin(origins = "https://indianpremierleague.azurewebsites.net")
    @GetMapping("/teams")
    public List<String> getUniqueTeams() {
        return matchService.getAllUniqueTeams();
    }

    @CrossOrigin(origins = "https://indianpremierleague.azurewebsites.net")
    @GetMapping("/seasons")
    public List<Integer> getUniqueSeasons() {
        return matchService.getAllUniqueSeasons();
    }

    @GetMapping("/season/{season}/team/{teamName}")
    @CrossOrigin(origins = "https://indianpremierleague.azurewebsites.net")
    public ResponseEntity<List<MatchEntity>> getMatchesBySeasonAndTeam(@PathVariable Long season, @PathVariable String teamName) {
        List<MatchEntity> matches = matchService.getAllMatchesBySeasonAndTeam(season, teamName);
        if (matches.isEmpty()) {
            return ResponseEntity.noContent().build(); // or return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matches);
    }

    @CrossOrigin(origins = "https://indianpremierleague.azurewebsites.net")
    @GetMapping("/season/{season}/team/{teamName}/winPercentage")
    public ResponseEntity<Double> getWinPercentage(@PathVariable Long season, @PathVariable String teamName) {
        double winPercentage = matchService.calculateWinPercentage(season, teamName);
        return ResponseEntity.ok(winPercentage);
    }

    @CrossOrigin(origins = "https://indianpremierleague.azurewebsites.net")
    @GetMapping("/season/{season}/team/{teamName}/lossPercentage")
    public ResponseEntity<Double> getLossPercentage(@PathVariable Long season, @PathVariable String teamName) {
        double lossPercentage = matchService.calculateLossPercentage(season, teamName);
        return ResponseEntity.ok(lossPercentage);
    }
}
