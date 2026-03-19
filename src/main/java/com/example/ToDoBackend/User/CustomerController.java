package com.example.ToDoBackend.User;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CustomerController {
  @Autowired
  private CustomerService customerService;
    @PostMapping("/Register")
    public boolean register(@RequestBody Customer customer) throws MessagingException, IOException {
        return customerService.customerRegistration(customer);

  }
}
