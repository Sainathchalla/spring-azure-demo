package com.IPL.MajorProject.RepositoryPkg;

import com.IPL.MajorProject.EntityPkg.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long> {
    List<MatchEntity> findBySeason(Long season);

    List<MatchEntity> findByTeam1OrTeam2(String team1, String team2);

    @Query("SELECT m FROM MatchEntity m WHERE m.season = :season AND (m.team1 = :team OR m.team2 = :team)")
    List<MatchEntity> findBySeasonAndTeam(@Param("season") Long season, @Param("team") String team);
}
