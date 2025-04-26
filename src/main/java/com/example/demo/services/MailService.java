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



    @Value("${MAIL_TOKEN}")
    String mailToken;

    public String sendMail(String mail, String name){

        // you can also add multiple recipients by calling addRecipient again
        // email.addRecipient("WilliamReceiver", "wbence1@gmail.com");

        // there's also a recipient object you can use
        // Recipient recipient = new Recipient("name", "your@recipient3.com");
        // email.AddRecipient(recipient);



        Email email = new Email();

        email.setFrom("Cabous & Jeanine", "cabous@test-pzkmgq7omk2l059v.mlsender.net");
        email.addRecipient(name, mail);
        //
        //        uncommment in prod --->> email.addRecipient(name, mail); <<----
        //
        //
        //        email.addRecipient("Willem", "willem04011999@gmail.com");
        //        for prod
        email.setSubject("Cabous & Jeanine RSVP Successful");

        email.setPlain("Baie dankie dat julle ons webwerf gebruik het om te RSVP");
        email.setHtml("<p>Baie dankie dat julle ons webwerf gebruik het om te RSVP. Hierdie e-pos is om te bevestig dat ons jou RSVP onvang het. As jy enige vra het. Asseblief kontak ons op 076 018 6629 </p>");

        MailerSend ms = new MailerSend();

        ms.setToken(mailToken);

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
