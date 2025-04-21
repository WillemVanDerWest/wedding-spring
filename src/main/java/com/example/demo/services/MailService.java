package com.example.demo.services;

import com.example.demo.Repositories.RsvpuserRepository;
import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.emails.Email;
import com.mailersend.sdk.exceptions.MailerSendException;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MailService {

//    @Value("${MAIL_TOKEN}")
//    private String mailToken;

    public String sendMail(String mail, String name){

        Email email = new Email();

        email.setFrom("Cabous & Jeanine", "cabous@test-pzkmgq7omk2l059v.mlsender.net");
        email.addRecipient(name, mail);

        // you can also add multiple recipients by calling addRecipient again
        // email.addRecipient("WilliamReceiver", "wbence1@gmail.com");

        // there's also a recipient object you can use
        // Recipient recipient = new Recipient("name", "your@recipient3.com");
        // email.AddRecipient(recipient);

        email.setSubject("Cabous & Jeanine RSVP Notification");

        email.setPlain("Baie dankie dat julle ons webwerf gebruik het om te RSVP");
        email.setHtml("<p>This is the HTML content</p>");

        MailerSend ms = new MailerSend();

        ms.setToken("mlsn.0ac18c3bb7d0d913dcdbeaa3274af928c4c7542175465291aa99ec5fe0af0830");

        try {
            MailerSendResponse response = ms.emails().send(email);
            System.out.println(response.messageId);
            return "Mail has been sent";
        } catch (MailerSendException e) {
            e.printStackTrace();
            return "Mail failed";
        }

    }
}
