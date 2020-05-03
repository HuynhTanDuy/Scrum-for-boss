package spring.phase2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.phase2.entity.ResponseData;
import spring.phase2.entity.Task;
import spring.phase2.request.LogWorkRequest;
import spring.phase2.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController extends Controller implements IController<Task> {
	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public ResponseEntity<ResponseData> getAll() {
		try {
			responseData.setResponse(GET_SUCCESS_MESSAGE, taskService.getAll(), HttpStatus.OK);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.OK);
		} catch (Exception e) {
			responseData.setResponse(FAILED + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<ResponseData> getById(@PathVariable int id) {
		try {
			responseData.setResponse(GET_SUCCESS_MESSAGE, taskService.getById(id), HttpStatus.OK);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.OK);
		} catch (Exception e) {
			responseData.setResponse(FAILED + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<ResponseData> create(@RequestBody Task task) {
		try {
			responseData.setResponse(CREATE_SUCCESS_MESSAGE, taskService.create(task), HttpStatus.CREATED);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.CREATED);
		} catch (Exception e) {
			responseData.setResponse(FAILED + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
 	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseData> update(@RequestBody Task new_task, @PathVariable Integer id) {
		try {
			responseData.setResponse(UPDATE_DATA_MESSAGE, taskService.update(new_task, id), HttpStatus.OK);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.OK);
		} catch (Exception e) {
			responseData.setResponse(FAILED + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseData> deleteById(@PathVariable Integer id) {
		try {
			taskService.deleteById(id);
			responseData.setResponse(DELETE_SUCCESS_MESSAGE, null, HttpStatus.OK);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.OK);
		} catch (Exception e) {
			responseData.setResponse(FAILED + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/assign/{id}")
	public ResponseEntity<ResponseData> assignForUser(@RequestBody int user_id, @PathVariable int id) {
		try {
			responseData.setResponse(UPDATE_DATA_MESSAGE, taskService.assignForUser(user_id, id), HttpStatus.OK);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.OK);
		} catch (Exception e) {
			responseData.setResponse(FAILED + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/setStatus/{id}")
	public ResponseEntity<ResponseData> setStatus(@RequestBody int status, @PathVariable int id) {
		try {
			responseData.setResponse(UPDATE_DATA_MESSAGE, taskService.setStatus(status, id), HttpStatus.OK);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.OK);
		} catch (Exception e) {
			responseData.setResponse(FAILED + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/logwork/{id}")
	public ResponseEntity<ResponseData> logWork(@RequestBody LogWorkRequest log_request, @PathVariable int id) {
		try {
			responseData.setResponse(UPDATE_DATA_MESSAGE, taskService.logWork(log_request, id), HttpStatus.OK);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.OK);
		} catch (Exception e) {
			responseData.setResponse(FAILED + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
