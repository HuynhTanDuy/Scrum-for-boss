package spring.phase2.controller;

import javax.validation.Valid;

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
import spring.phase2.entity.User;
import spring.phase2.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends Controller implements IController<User> {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<ResponseData> getAll() {
		try {
			responseData.setResponse(GET_SUCCESS_MESSAGE, userService.getAll(), HttpStatus.OK);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.OK);
		} catch (Exception e) {
			responseData.setResponse(FAILED + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<ResponseData> getById(@PathVariable int id) {
		try {
			responseData.setResponse(GET_SUCCESS_MESSAGE, userService.getById(id), HttpStatus.OK);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.OK);
		} catch (Exception e) {
			responseData.setResponse(FAILED + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<ResponseData> create(@Valid @RequestBody User user) {
		try {
			responseData.setResponse(CREATE_SUCCESS_MESSAGE, userService.create(user), HttpStatus.CREATED);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.CREATED);
		} catch (Exception e) {
			responseData.setResponse(FAILED + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseData> update(@Valid @RequestBody User new_user, @PathVariable Integer id) {
		try {
			responseData.setResponse(UPDATE_DATA_MESSAGE, userService.update(new_user, id), HttpStatus.OK);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.OK);
		} catch (Exception e) {
			responseData.setResponse(FAILED + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseData> deleteById(@PathVariable Integer id) {
		try {
			userService.deleteById(id);
			responseData.setResponse(DELETE_SUCCESS_MESSAGE, null, HttpStatus.OK);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.OK);
		} catch (Exception e) {
			responseData.setResponse(FAILED + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
