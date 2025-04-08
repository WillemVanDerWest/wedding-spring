package com.example.demo.Repositories;

import com.example.demo.Entities.RsvpUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface RsvpuserRepository extends JpaRepository<RsvpUser, Long> {
    List<RsvpUser> findByNameAndSurname(String name, String surname);

}
