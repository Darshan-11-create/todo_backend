package com.example.ToDoBackend.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class sendGrid {

    @Value("${sendgrid.api.key}")
    private String apiKey;

    @Value("${sendgrid.from.email}")
    private String fromEmail;

    public String getApiKey() {
        return apiKey;
    }

    public String getFromEmail() {
        return fromEmail;
    }
}