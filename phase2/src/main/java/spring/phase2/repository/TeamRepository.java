package spring.phase2.repository;

import org.springframework.data.repository.CrudRepository;

import spring.phase2.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Integer> {
}
