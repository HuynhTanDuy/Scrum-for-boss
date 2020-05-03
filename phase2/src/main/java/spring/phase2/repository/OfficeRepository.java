package spring.phase2.repository;

import org.springframework.data.repository.CrudRepository;

import spring.phase2.entity.Office;

public interface OfficeRepository extends CrudRepository<Office, Integer> {
}
