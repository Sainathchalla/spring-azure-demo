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

    public List<MatchEntity> getAllMatchesBySeason(Long season) {
        return matchRepo.findBySeason(season);
    }

    public List<MatchEntity> getAllMatchesByTeam(String teamName) {
        return matchRepo.findByTeam1OrTeam2(teamName, teamName);
    }

    public List<MatchEntity> getAllMatchesBySeasonAndTeam(Long season, String teamName) {
        return matchRepo.findBySeasonAndTeam(season, teamName);
    }
}
