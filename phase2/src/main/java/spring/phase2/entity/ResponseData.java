package spring.phase2.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseData {
	private Map<String, Object> response = new HashMap<>();
	
	public Map<String, Object> getResponse() {
		return response;
	}

	public void setResponse(String message, Object data, HttpStatus status) {
		response.put("message", message);
		if (data!=null) {
			response.put("data", data);
		}
		response.put("status" , status.value());
	}
}
