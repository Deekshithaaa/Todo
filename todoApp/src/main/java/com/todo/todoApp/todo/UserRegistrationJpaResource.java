package com.todo.todoApp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserRegistrationJpaResource {
	
	@Autowired
    private EmailService emailService;
	
	private UserRegistrationRepo userRegistrationRepo;
	
	public UserRegistrationJpaResource (UserRegistrationRepo userRegistrationRepo) {
		
		this.userRegistrationRepo = userRegistrationRepo;
		
	
	}
	
	@PostMapping("/users")
    public UserRegistration createUser(@RequestBody UserRegistration userRegistration) {
		UserRegistration savedUser = userRegistrationRepo.save(userRegistration);
		try {
	        emailService.sendSimpleEmail(
	            savedUser.getMail_id(),
	            "Welcome to Todo App",
	            "Hello " + savedUser.getUsername() + ",\n\nThank you for registering to Todo App!"
	        );
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	    }
        return savedUser;
    	
    }
	
	
	@PostMapping("/users/checkUsernameAndPassword")
    public boolean checkUsernameAndPasswordMatch(@RequestBody UserRegistration userRegistration) {
        UserRegistration user = userRegistrationRepo.findByUsernameAndPassword(userRegistration.getUsername(), userRegistration.getPassword());
        return user != null;
    }
	
	
	@PostMapping("/users/checkUsername")
    public boolean checkUsername(@RequestBody UserRegistration userRegistration) {
        UserRegistration user = userRegistrationRepo.findByUsername(userRegistration.getUsername());
        return user != null;
    }
	
	



}
