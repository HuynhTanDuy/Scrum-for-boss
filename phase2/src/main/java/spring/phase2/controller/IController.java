package spring.phase2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import spring.phase2.entity.ResponseData;
import spring.phase2.entity.User;

public interface IController<T> {
	ResponseEntity<ResponseData> getAll();
	ResponseEntity<ResponseData> getById(int id);
	ResponseEntity<ResponseData> create(T t);
	ResponseEntity<ResponseData> update(T t, Integer id);
	ResponseEntity<ResponseData> deleteById(Integer id);
}
