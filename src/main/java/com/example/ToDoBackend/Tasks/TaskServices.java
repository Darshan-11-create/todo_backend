package com.example.ToDoBackend.Tasks;

import com.example.ToDoBackend.EmailService.emailService;
import com.example.ToDoBackend.TaskShedular.taskShedular;
import com.example.ToDoBackend.User.Customer;
import com.example.ToDoBackend.User.CustomerRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
@Service
public class TaskServices {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
     private taskShedular taskShedular;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
      private emailService emailService;
    public void addTask(Task task) throws MessagingException, IOException {
        Customer c =customerRepository.findById(task.getCustomer().getId());
        if(c==null){
            System.out.println("Customer not found");
          return;
        }
        task.setCustomer(c);
        task.setNextRemainder(task.getWhenToStartNotifications());
        taskRepository.save(task);
        taskShedular.updateNextReminder(task);
        emailService.sendHtmlEmail(c.getEmail(),"Task Added Successfully","Task Details  Task Name:- "+task.getTaskName()+
                "Date OF Task  "+task.getDateOfTask());
    }
    public List<Task> getTasks(int id){
        return customerRepository.findById(id).getTaskList();
    }
    public void removeTask(int task_id){
        Task t= taskRepository.findById(task_id);
        if(t==null)
            throw new RuntimeException("Task Not Found");
        taskRepository.delete(t);
    }
}
