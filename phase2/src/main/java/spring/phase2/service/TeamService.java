package spring.phase2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import spring.phase2.entity.Team;
import spring.phase2.entity.Team_User;
import spring.phase2.repository.TeamRepository;
import spring.phase2.repository.TeamUserRepository;
import spring.phase2.request.TeamRequest;

@Service
public class TeamService {
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private TeamUserRepository teamUserRepository;
		
	public List<Team> getAll() {
		return (List<Team>) teamRepository.findAll();
	}
	
	public Optional<Team> getById(int id) {
		return teamRepository.findById(id);
	}
	
	public Team create(TeamRequest team_request) {
		Team team = new Team();
		team.setName(team_request.getName());
		team.setOffice_id(team_request.getOffice_id());
		Team saved_team = teamRepository.save(team);
		
		List<Integer> users = team_request.getUsers();
		for (Integer user : users) {
			Team_User team_user = new Team_User();
			team_user.setTeam_id(saved_team.getId());
			team_user.setUser_id(user);
			teamUserRepository.save(team_user);
		}
		return saved_team;
	}
	
	public Team update(TeamRequest team_request, Integer id) {
		Team team = teamRepository.findById(id).get();
		team.setName(team_request.getName());
		team.setOffice_id(team_request.getOffice_id());
		Team saved_team = teamRepository.save(team);
		
		teamUserRepository.deleteByTeamId(id);
		List<Integer> users = team_request.getUsers();
		for (Integer user : users) {
			Team_User team_user = new Team_User();
			team_user.setTeam_id(saved_team.getId());
			team_user.setUser_id(user);
			teamUserRepository.save(team_user);
		}
		return saved_team;
	}
		
	public void deleteById(Integer id) {
	    teamRepository.deleteById(id);
	}
}
