package com.todo.todoApp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface TodoRepository extends JpaRepository<Todo, Integer>{
    
    List<Todo> findByUsername(String username);
    List<Todo> findByTargetDate(LocalDate targetDate);
    List<Todo> findByTargetDateGreaterThan(LocalDate date);
    List<Todo> findByUsernameOrderByPriorityAsc(String username);
    List<Todo> findByUsernameAndPriority(String username, String priority);
    List<Todo> findByUsernameAndTags(String username, String tags);
    List<Todo> findByUsernameAndDone(String username, boolean done);
    List<Todo> findByDoneAndTargetDateBefore(boolean done, LocalDate targetDate);
    @Query("SELECT DISTINCT t.tags FROM Todo t WHERE t.username = :username AND t.tags IS NOT NULL")
    List<String> findAllDistinctTagsByUsername(String username);

}