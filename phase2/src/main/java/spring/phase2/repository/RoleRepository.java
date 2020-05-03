package spring.phase2.repository;

import org.springframework.data.repository.CrudRepository;

import spring.phase2.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}

