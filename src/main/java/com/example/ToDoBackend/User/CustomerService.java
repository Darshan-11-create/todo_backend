package com.example.ToDoBackend.User;

import com.example.ToDoBackend.EmailService.emailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
@Service
public class CustomerService {
  @Autowired
    private CustomerRepository customerRepository;
  @Autowired
   private emailService emailService;
  public boolean customerRegistration(Customer customer) throws MessagingException, IOException {
      Customer c=customerRepository.findByUserName(customer.getUserName());
      if(c!=null)
          return false;
      customer.setTaskList(new ArrayList<>());
      customerRepository.save(customer);
       emailService.sendHtmlEmail(customer.getEmail(),"Registration is success","Welcome manage ur tasks");
     return true;
  }
}
