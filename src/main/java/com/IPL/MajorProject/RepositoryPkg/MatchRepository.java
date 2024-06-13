package com.IPL.MajorProject.RepositoryPkg;

import com.IPL.MajorProject.EntityPkg.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

    @Query("SELECT DISTINCT team1 FROM MatchEntity")
    List<String> findUniqueTeams();

    @Query("SELECT DISTINCT season FROM MatchEntity")
    List<Integer> findUniqueSeasons();

    @Query("SELECT m FROM MatchEntity m WHERE m.season = :season AND (m.team1 = :team OR m.team2 = :team)")
    List<MatchEntity> findBySeasonAndTeam(@Param("season") Long season, @Param("team") String team);

    @Query("SELECT COUNT(m) FROM MatchEntity m WHERE m.season = :season AND m.winner = :team")
    int countWinsByTeamAndSeason(@Param("season") Long season, @Param("team") String team);

    @Query("SELECT COUNT(m) FROM MatchEntity m WHERE m.season = :season AND (m.team1 = :team OR m.team2 = :team) AND m.winner != :team")
    int countLossesByTeamAndSeason(@Param("season") Long season, @Param("team") String team);
}
