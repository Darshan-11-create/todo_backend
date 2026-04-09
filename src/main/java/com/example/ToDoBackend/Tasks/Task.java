package com.example.ToDoBackend.Tasks;

import com.example.ToDoBackend.User.Customer;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int task_id;

    private String taskName;

    private LocalDateTime dateOfTask;

    private int gapForNotifications;

    private LocalDateTime whenToStartNotifications;
    private LocalDateTime nextRemainder=whenToStartNotifications;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    public Task(){

    }

    public LocalDateTime getNextRemainder() {
        return nextRemainder;
    }

    public void setNextRemainder(LocalDateTime nextRemainder) {
        this.nextRemainder =nextRemainder;
    }

    public LocalDateTime getWhenToStartNotifications() {
        return whenToStartNotifications;
    }

    public void setWhenToStartNotifications(LocalDateTime whenToStartNotifications) {
        this.whenToStartNotifications = whenToStartNotifications;
        setNextRemainder(whenToStartNotifications);
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public LocalDateTime getDateOfTask() {
        return dateOfTask;
    }

    public void setDateOfTask(LocalDateTime dateOfTask) {
        this.dateOfTask = dateOfTask;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getGapForNotifications() {
        return gapForNotifications;
    }

    public void setGapForNotifications(int gapForNotifications) {
        this.gapForNotifications = gapForNotifications;
    }

}