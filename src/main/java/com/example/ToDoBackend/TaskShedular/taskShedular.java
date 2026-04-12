package com.example.ToDoBackend.TaskShedular;

import com.example.ToDoBackend.EmailService.emailService;
import com.example.ToDoBackend.Tasks.Task;
import com.example.ToDoBackend.Tasks.TaskRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
@Component
public class taskShedular {

    @Autowired
     private TaskRepository taskRepository;
    @Autowired
     private emailService emailService;
    @Scheduled(fixedRate = 60000)
    public void checkTasks() throws MessagingException, IOException {

        LocalDateTime now = LocalDateTime.now();
        List<Task> tasks = taskRepository.findAll();
        for(Task t:tasks){
            System.out.println("Sending email to"+t.getTaskName()+" "+t.getNextRemainder());
            if(t.getNextRemainder()!=null && !t.getNextRemainder().isAfter(t.getDateOfTask()) && !t.getNextRemainder().isAfter(now)){
                System.out.println("Sending email to COme into if cond");
                emailService.sendHtmlEmail(t.getCustomer().getEmail(),"Alert:-Remainder","Hello there remainder for ur task  Task Name:-  "+t.getTaskName());
               updateNextReminder(t);
            }
        }

    }
    public void updateNextReminder(Task task){

        LocalDateTime next =
                task.getNextRemainder()
                        .plusMinutes(task.getGapForNotifications());
        if(next.isAfter(task.getDateOfTask())){
            task.setNextRemainder(null); // stop reminders
        }else{
            task.setNextRemainder(next);
        }

        taskRepository.save(task);
    }
}
