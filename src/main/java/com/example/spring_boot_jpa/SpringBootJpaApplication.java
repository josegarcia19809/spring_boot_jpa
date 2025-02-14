package com.example.spring_boot_jpa;

import com.example.spring_boot_jpa.entities.Person;
import com.example.spring_boot_jpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootJpaApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("-".repeat(100));
        List<Person> persons = (List<Person>) personRepository.findAll();
        persons.forEach(System.out::println);
        System.out.println("-".repeat(100));
    }
}
