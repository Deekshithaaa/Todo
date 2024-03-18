package com.todo.todoApp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TodoJpaResource {
	
	
	@Autowired
	private EmailService emailService;

	
	private TodoRepository todoRepository;
	
	public TodoJpaResource( TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
	
	@GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
        return todoRepository.findByUsername(username);
    }
	
	@GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username,
            @PathVariable int id) {
		return todoRepository.findById(id).get();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username,
            @PathVariable int id) {
    	todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
        
    }
    
    
    
    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username,
            @PathVariable int id, @RequestBody Todo todo) {
    	todoRepository.save(todo);
        return todo;
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username,
             @RequestBody Todo todo) {
    	todo.setUsername(username);
        todo.setId(null);
        return todoRepository.save(todo);
    }
    
    @GetMapping("/users/{username}/todos/today")
    public List<Todo> retrieveTodosForToday(@PathVariable String username) {
        LocalDate today = LocalDate.now();
        return todoRepository.findByTargetDate(today);
    }
    
    @GetMapping("/users/{username}/todos/upcoming")
    public List<Todo> retrieveUpcomingTodos(@PathVariable String username) {
        LocalDate today = LocalDate.now();
        return todoRepository.findByTargetDateGreaterThan(today);
    }
    
    @GetMapping("/users/{username}/todos/sorted")
    public List<Todo> retrieveTodosSortedByPriority(@PathVariable String username) {
        return todoRepository.findByUsernameOrderByPriorityAsc(username);
    }
    
    @GetMapping("/users/{username}/todos/priority/{priority}")
    public List<Todo> retrieveTodosByPriority(@PathVariable String username, @PathVariable String priority) {
        return todoRepository.findByUsernameAndPriority(username, priority);
    }
    
    @GetMapping("/users/{username}/todos/tags/{tag}")
    public List<Todo> retrieveTodosByTag(@PathVariable String username, @PathVariable String tag) {
        return todoRepository.findByUsernameAndTags(username, tag);
    }
    
    @GetMapping("/users/{username}/todos/tags")
    public List<String> retrieveAllAvailableTagsByUsername(@PathVariable String username) {
        return todoRepository.findAllDistinctTagsByUsername(username);
    }
    
    
    @GetMapping("/users/{username}/todos/done")
    public List<Todo> retrieveCompletedTodos(@PathVariable String username) {
        return todoRepository.findByUsernameAndDone(username, true);
    }

    @GetMapping("/users/{username}/todos/not-done")
    public List<Todo> retrieveUncompletedTodos(@PathVariable String username) {
        return todoRepository.findByUsernameAndDone(username, false);
    }
    
    @GetMapping("/users/{username}/todos/pending-past")
    public List<Todo> retrievePendingTodosBeforeToday(@PathVariable String username) {
        LocalDate today = LocalDate.now();
        return todoRepository.findByDoneAndTargetDateBefore(false, today);
    }

    

}
