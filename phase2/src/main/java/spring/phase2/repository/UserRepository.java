package spring.phase2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import spring.phase2.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	@Query(value = "SELECT * FROM user u WHERE u.name = :username", 
			nativeQuery = true)
	User findByUserName(@Param("username") String username);
}
