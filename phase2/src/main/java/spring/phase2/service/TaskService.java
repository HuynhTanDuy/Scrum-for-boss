package spring.phase2.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.phase2.entity.Task;
import spring.phase2.repository.TaskRepository;
import spring.phase2.request.LogWorkRequest;
import spring.phase2.entity.Log_Work;
import spring.phase2.repository.LogWorkRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private LogWorkRepository logWorkRepository;
	
	private static final int TODO = 1;
	private static final int IN_PROGRESS = 2;
	private static final int DONE = 3;
	
	public List<Task> getAll() {
		return (List<Task>) taskRepository.findAll();
	}
	
	public Optional<Task> getById(int id) {
		return taskRepository.findById(id);
	}
	
	public Task create(Task task) {
		return taskRepository.save(task);
	}
	
	public Task update(Task new_task, Integer id) {
		return taskRepository.findById(id)
	        .map(task -> {
	    	     new_task.setId(task.getId());
	    	     return taskRepository.save(new_task);
	        })
	        .orElseGet(() -> {
	    	     new_task.setId(id);
	    	     return taskRepository.save(new_task);
	        });
	}
		
	public void deleteById(Integer id) {
	    taskRepository.deleteById(id);
	}
	
	public Task assignForUser(Integer user_id, Integer id) {
		Optional<Task> task = taskRepository.findById(id);
		task.get().setUser_id(user_id);
	    return taskRepository.save(task.get());   
	}
	
	public Task setStatus(Integer status, Integer id) {
		Optional<Task> task = taskRepository.findById(id);
		task.get().setStatus(status);
		if (status == IN_PROGRESS) {
			LocalDateTime now =   LocalDateTime.now();  
			task.get().setIn_progress_at(now);
		}
		return taskRepository.save(task.get());   
	}
	
	public Task logWork(LogWorkRequest log_request, Integer id) {
		List<Log_Work> logs = log_request.getLogs();
		int actual_time = 0;
		for (Log_Work log : logs) {
			log.setId_task(id);
			logWorkRepository.save(log);
			actual_time += log.getSpended_time();
		}
		Task task = taskRepository.findById(id).get();
		task.setActual_time(actual_time);
		return taskRepository.save(task);
	}
}
