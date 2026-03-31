package com.example.ToDoBackend.EmailService;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;

@Service
public class emailService {

    @Value("${sendgrid.api.key}")
    private String apiKey;

    @Value("${sendgrid.from.email}")
    private String fromEmail;

    @Async
    public void sendSimpleEmail(String to, String subject, String body) throws IOException {

        Email from = new Email(fromEmail);
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", body);

        Mail mail = new Mail(from, subject, toEmail, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        sg.api(request);
    }

    @Async
    public void sendHtmlEmail(String to, String subject, String htmlBody) throws MessagingException, IOException {
        String helper1 = """
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Welcome to TaskFlow</title>
  <link href="https://fonts.googleapis.com/css2?family=DM+Serif+Display:ital@0;1&family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet"/>
</head>
<body style="margin:0;padding:0;background-color:#0f0f0f;font-family:'DM Sans',sans-serif;">

  <table width="100%" cellpadding="0" cellspacing="0" style="background:#0f0f0f;padding:60px 20px;">
    <tr>
      <td align="center">
        <table width="520" cellpadding="0" cellspacing="0" style="max-width:520px;width:100%;">

          <tr>
            <td style="background:linear-gradient(135deg,#c8f550 0%,#a8e040 100%);height:5px;border-radius:12px 12px 0 0;"></td>
          </tr>

          <tr>
            <td style="background:#1a1a1a;border-radius:0 0 12px 12px;padding:52px 48px;border:1px solid #2a2a2a;border-top:none;text-align:center;">

              <span style="display:inline-block;width:52px;height:52px;background:#c8f550;border-radius:50%;text-align:center;line-height:52px;font-size:24px;margin-bottom:28px;">✓</span>

              <h1 style="margin:0 0 16px;font-family:'DM Serif Display',serif;font-size:32px;color:#ffffff;font-weight:400;">
              
              """+subject+
              """
              </h1>

              <p style="margin:0;font-size:15px;color:#888;line-height:1.6;">"""+
                htmlBody+
              """
              </p>

              <table cellpadding="0" cellspacing="0" style="margin:36px auto 0;">
                <tr>
                  <td style="border-radius:8px;background:#c8f550;">
                    <a href="https://darshan-11-create.github.io/todo-app/" style="display:inline-block;padding:14px 32px;font-family:'DM Sans',sans-serif;font-size:14px;font-weight:600;color:#0f0f0f;text-decoration:none;border-radius:8px;">
                      Go to App →
                    </a>
                  </td>
                </tr>
              </table>

            </td>
          </tr>

        </table>
      </td>
    </tr>
  </table>

</body>
</html>
""";
        Email from = new Email(fromEmail);
        Email toEmail = new Email(to);
        Content content = new Content("text/html",helper1);

        Mail mail = new Mail(from, subject, toEmail, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        sg.api(request);
    }
}