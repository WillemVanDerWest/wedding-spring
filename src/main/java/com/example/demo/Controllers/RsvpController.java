package com.example.demo.Controllers;

import com.example.demo.Entities.RsvpUser;
import com.example.demo.Repositories.RsvpuserRepository;
import com.example.demo.services.MailService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
public class RsvpController {

    @Getter
    private final MailService mailService;

    private final RsvpuserRepository rsvpuserRepository;

    @Autowired
    public RsvpController(RsvpuserRepository rsvpuserRepository, MailService mailService){
        this.rsvpuserRepository = rsvpuserRepository;
        this.mailService = mailService;
    }

    @PostMapping("/newuser")
    public String addUser(@RequestBody RsvpUser prop){
        //add user to database
        return "Successfully ran the PostMapping for /newuser" + " ";
    }

    @GetMapping("/getusers")
    public ArrayList<RsvpUser> getUsers(){
        ArrayList<RsvpUser> users = new ArrayList<RsvpUser>();
       // List<RsvpUser> dbUsers = rsvpUserRepository.findAll();

//        for (int i = 0; i < 20; i++) {
//            RsvpUser iUser = new RsvpUser("name" + i, "surname" + i, new Date(), true, i + "@gmail.com", "none" + i);
//            users.add(iUser);
//        }
        return users;
    }

    @PostMapping("/addUser")
    public String postData(@RequestBody RsvpUser user){
        var newUser  = new RsvpUser(user.getName(),user.getSurname(),user.getDate(),user.getAttending(),user.getEmail(),user.getAllergens(),false);
        var database = rsvpuserRepository.findByNameAndSurname(newUser.getName(), newUser.getSurname());
        if (!database.isEmpty()){
            if (database.get(0).getDeleted()){
                database.get(0).setDeleted(false);
                rsvpuserRepository.save(database.get(0));
                return "User has been reactivated";
            } else {
                return "this user already exists";
            }
        }
        rsvpuserRepository.save(newUser);
        mailService.sendMail(newUser.getEmail(),newUser.getName());
        return "Check your mailbox";
    }

    @GetMapping("/getUser")
    public RsvpUser getData(){
        var data = rsvpuserRepository.findAll();
        return data.get(0);
    }

    @GetMapping("/getAllData")
    public List<RsvpUser> getAllData(){
        return rsvpuserRepository.findAll();
    }

    @DeleteMapping("/deleteUsers")
    public String deleteUsers(){
        rsvpuserRepository.deleteAll();
        return "You deleted all the users";
    }

    @PutMapping("/deleteUser")
    public String deleteUser(@RequestBody RsvpUser user){
        var rsvpUserFromDb = rsvpuserRepository.findById(user.getId());
        if (rsvpUserFromDb.isPresent()){
            rsvpUserFromDb.get().setDeleted(true);
            rsvpuserRepository.save(rsvpUserFromDb.get());
            return "User has been updated";
        }

        return "something went wrong";
    }
}
