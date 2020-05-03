package spring.phase2.repository;

import org.springframework.data.repository.CrudRepository;

import spring.phase2.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}
