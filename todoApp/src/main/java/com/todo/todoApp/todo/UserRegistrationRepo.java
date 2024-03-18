package com.todo.todoApp.todo;


import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRegistrationRepo extends JpaRepository<UserRegistration, Integer>{

	UserRegistration findByUsernameAndPassword(String username, String password);
	UserRegistration findByUsername(String username);
    

}