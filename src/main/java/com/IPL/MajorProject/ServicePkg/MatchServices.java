package com.IPL.MajorProject.ServicePkg;

import com.IPL.MajorProject.EntityPkg.MatchEntity;
import com.IPL.MajorProject.RepositoryPkg.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServices {

    @Autowired
    MatchRepository matchRepo;

    public List<MatchEntity> getAllMatchesBySeasonAndTeam(Long season, String teamName) {
        return matchRepo.findBySeasonAndTeam(season, teamName);
    }

    public List<String> getAllUniqueTeams() {
        return matchRepo.findUniqueTeams();
    }

    public List<Integer> getAllUniqueSeasons() {
        return matchRepo.findUniqueSeasons();
    }

    public int countWinsByTeamAndSeason(Long season, String teamName) {
        return matchRepo.countWinsByTeamAndSeason(season, teamName);
    }

    public int countLossesByTeamAndSeason(Long season, String teamName) {
        return matchRepo.countLossesByTeamAndSeason(season, teamName);
    }

    public double calculateWinPercentage(Long season, String teamName) {
        List<MatchEntity> matches = matchRepo.findBySeasonAndTeam(season, teamName);
        if (matches.isEmpty()) {
            return 0.0;
        }

        int totalMatches = matches.size();
        int wins = countWinsByTeamAndSeason(season, teamName);

        return (double) wins / totalMatches * 100;
    }

    public double calculateLossPercentage(Long season, String teamName) {
        List<MatchEntity> matches = matchRepo.findBySeasonAndTeam(season, teamName);
        if (matches.isEmpty()) {
            return 0.0;
        }

        int totalMatches = matches.size();
        int losses = countLossesByTeamAndSeason(season, teamName);

        return (double) losses / totalMatches * 100;
    }
}
