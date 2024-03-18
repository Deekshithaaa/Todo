package com.todo.todoApp.todo;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;  // Import Java's List

@Component
public class TodoReminderService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRegistrationRepo userRegistrationRepo;  

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 15 20 * * ?")  
    public void sendTodoReminders() {
        LocalDate today = LocalDate.now();
        List<Todo> todosDueToday = todoRepository.findByTargetDate(today);

        for (Todo todo : todosDueToday) {
            try {
                UserRegistration user = userRegistrationRepo.findByUsername(todo.getUsername()); 
                if (user != null) {
                    String email = user.getMail_id();

                    
                    StringBuilder emailBody = new StringBuilder();
                    emailBody.append("Dear ").append(todo.getUsername()).append(",\n\n");
                    emailBody.append("This is a gentle reminder about your upcoming task due today.\n\n");
                    emailBody.append("Details of the task:\n");
                    emailBody.append("Title: ").append(todo.getDescription()).append("\n");
                    emailBody.append("Priority: ").append(todo.getPriority()).append("\n");
                    emailBody.append("Comments: ").append(todo.getComments()).append("\n");
                    emailBody.append("Tags: ").append(todo.getTags()).append("\n");
                    emailBody.append("\nKindly ensure to review and complete the task on time.\n\n");
                    emailBody.append("Warm regards,\n");
                    emailBody.append("Your TodoApp Team");

                    emailService.sendSimpleEmail(
                        email,
                        "TodoApp Reminder: Task Due Today",
                        emailBody.toString()
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
