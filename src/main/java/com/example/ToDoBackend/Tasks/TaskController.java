package com.example.ToDoBackend.Tasks;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class TaskController {
  @Autowired
    private TaskServices taskServices;
  @PostMapping("/addTask")
    public void addTask(@RequestBody Task task) throws MessagingException, IOException {
      taskServices.addTask(task);
  }
  @GetMapping("/allTasks")
  public List<Task> getAllTasks(@RequestParam int id){
      return taskServices.getTasks(id);
  }
  @DeleteMapping("/removeTask")
    public void removeTask(@RequestParam int task_id){
       taskServices.removeTask(task_id);
  }
}
