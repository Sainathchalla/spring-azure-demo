package com.IPL.MajorProject.ControllerPkg;

import com.IPL.MajorProject.EntityPkg.MatchEntity;
import com.IPL.MajorProject.ServicePkg.MatchServices;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchController {

    @Autowired
    MatchServices matchService;

    @GetMapping("/season/{season}")
    public ResponseEntity<List<MatchEntity>> getMatchesBySeason(@PathVariable Long season) {
        List<MatchEntity> matches = matchService.getAllMatchesBySeason(season);
        if (matches.isEmpty()) {
            return ResponseEntity.noContent().build(); // or return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matches);
    }

    @GetMapping("team/{teamName}")
    public ResponseEntity<List<MatchEntity>> getMatchesByTeam(@PathVariable String teamName) {
        List<MatchEntity> matches = matchService.getAllMatchesByTeam(teamName);
        if (matches.isEmpty()) {
            return ResponseEntity.noContent().build(); // or return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/season/{season}/team/{teamName}")
    public ResponseEntity<List<MatchEntity>> getMatchesBySeasonAndTeam(@PathVariable Long season, @PathVariable String teamName) {
        List<MatchEntity> matches = matchService.getAllMatchesBySeasonAndTeam(season, teamName);
        if (matches.isEmpty()) {
            return ResponseEntity.noContent().build(); // or return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matches);
    }

}
