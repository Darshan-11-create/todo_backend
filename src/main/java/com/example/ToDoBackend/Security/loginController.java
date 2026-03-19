package com.example.ToDoBackend.Security;

import com.example.ToDoBackend.User.Customer;
import com.example.ToDoBackend.User.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class loginController {
  @Autowired
    private login login;
  @Autowired
   private CustomerRepository customerRepository;
  @PostMapping("/login")
    public ResponseEntity<?> loginHelper(@RequestBody login login){
      String pass=login.getPassword();
      Customer c=customerRepository.findByUserName(login.getUserName());
      if(c==null) {
         return ResponseEntity.status(404).body("User Not Found");
      }
          if(!pass.equals(c.getPassword())) {
              return ResponseEntity.status(404).body("Password Incorrect");
          }
    return ResponseEntity.status(200).body(c.getId());
  }
  @PutMapping("/forgetPassword")
    public ResponseEntity<?> emailVerify(@RequestParam String username,@RequestParam String password){
      Customer c=customerRepository.findByUserName(username);
      if(c==null){
          return ResponseEntity.status(404).body("UserName Doesnot Exist");
      }
      c.setPassword(password);
      customerRepository.save(c);
      return ResponseEntity.status(200).body("Password Updated Successfully");
  }
}
