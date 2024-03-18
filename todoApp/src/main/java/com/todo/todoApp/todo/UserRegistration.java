package com.todo.todoApp.todo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class UserRegistration {
	
	public UserRegistration() {
		
	}
	
	public UserRegistration(Integer id, String username, String password, String mail_id) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.mailid = mail_id;
	}
	
	@Id
    @GeneratedValue
    private Integer id;
	private String username;
	private String password;
	private String mailid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail_id() {
		return mailid;
	}
	public void setMail_id(String mail_id) {
		this.mailid = mail_id;
	}
	

}
