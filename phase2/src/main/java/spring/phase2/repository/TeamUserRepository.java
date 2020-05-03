package spring.phase2.repository;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import spring.phase2.entity.Team_User;

public interface TeamUserRepository extends CrudRepository<Team_User, Integer> {
	@Query(value = "Delete FROM team_user tu WHERE tu.team_id = :team_id", 
			nativeQuery = true)
	@Modifying
    @Transactional
	public void deleteByTeamId(@Param("team_id") int teamid);
}
