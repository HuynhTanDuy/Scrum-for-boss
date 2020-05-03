package spring.phase2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.*;
import javax.validation.constraints.NotEmpty;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task {
	private static final String NULL_MSG = "cannot be null";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Title " + NULL_MSG)
	private String title;
	
	private String description;
	
	private int estimate_time;
	
	private int status;
	private int actual_time;
	
	@Column(columnDefinition="Integer default null")
	private Integer user_id;
	
	private LocalDateTime in_progress_at;
	
	@CreationTimestamp
	private LocalDateTime created_at;
	
	@UpdateTimestamp
	private LocalDateTime updated_at;
	
	public int getActual_time() {
		return actual_time;
	}
	public void setActual_time(int actual_time) {
		this.actual_time = actual_time;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public LocalDateTime getIn_progress_at() {
		return in_progress_at;
	}
	public void setIn_progress_at(LocalDateTime in_progress_at) {
		this.in_progress_at = in_progress_at;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
		
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getEstimate_time() {
		return estimate_time;
	}
	public void setEstimate_time(int estimate_time) {
		this.estimate_time = estimate_time;
	}
	
}
