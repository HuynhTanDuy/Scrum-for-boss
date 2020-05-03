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
import spring.phase2.request.TeamRequest;
import spring.phase2.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController extends Controller implements IController<TeamRequest> {
	
	@Autowired
	private TeamService teamService;
	
	@GetMapping
	public ResponseEntity<ResponseData> getAll() {
		try {
			responseData.setResponse(GET_SUCCESS_MESSAGE, teamService.getAll(), HttpStatus.OK);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(FAILED + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<ResponseData> getById(@PathVariable int id) {
		try {
			responseData.setResponse(GET_SUCCESS_MESSAGE, teamService.getById(id), HttpStatus.OK);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(FAILED + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<ResponseData> create(@RequestBody TeamRequest team_request) {
		try {
			responseData.setResponse(CREATE_SUCCESS_MESSAGE, teamService.create(team_request), HttpStatus.CREATED);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(FAILED + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
 	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseData> update(@RequestBody TeamRequest team_request, @PathVariable Integer id) {
		try {
			responseData.setResponse(UPDATE_DATA_MESSAGE, teamService.update(team_request, id), HttpStatus.OK);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(FAILED + ". " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseData> deleteById(@PathVariable Integer id) {
		try {
			teamService.deleteById(id);
			responseData.setResponse(DELETE_SUCCESS_MESSAGE, null, HttpStatus.OK);
			return new ResponseEntity(responseData.getResponse(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(FAILED + ". " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
