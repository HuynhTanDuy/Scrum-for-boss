package auth.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import auth.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	@Query(value = "SELECT * FROM user u WHERE u.name = :username", 
			nativeQuery = true)
	User findByUserName(@Param("username") String username);
}
