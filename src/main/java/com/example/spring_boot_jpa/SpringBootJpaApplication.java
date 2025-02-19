package com.example.spring_boot_jpa;

import com.example.spring_boot_jpa.entities.Person;
import com.example.spring_boot_jpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

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
        // List<Person> persons = (List<Person>) personRepository.findAll();
        // List<Person> persons = (List<Person>) personRepository.findByProgrammingLanguage("Python");
        // List<Person> persons = (List<Person>) personRepository
        //        .buscarByProgrammingLanguage("Java", "Andres");
        List<Person> persons = (List<Person>) personRepository
                .findByProgrammingLanguageAndName("Python", "Pepe");
        persons.forEach(System.out::println);
        System.out.println("-".repeat(100));

        List<Object[]> personsValues = personRepository.obtenerPersonData();
        personsValues.forEach(person -> {
            System.out.println(person[0] + " es experto en " + person[1]);
        });
        System.out.println("-".repeat(100));

        List<Object[]> personsValuesProgrammingAndName = personRepository.obtenerPersonData("Java", "Andres");
        personsValuesProgrammingAndName.forEach(person -> {
            System.out.println(person[0] + " es experto en " + person[1]);
        });
        System.out.println("-".repeat(100));

        List<Object[]> personsValuesByName = personRepository.obtenerPersonDataByName("Maria");
        personsValuesByName.forEach(person -> {
            System.out.println(person[0] + " es experto en " + person[1]);
        });
        System.out.println("-".repeat(100));

        List<Object[]> personsValuesByLanguage = personRepository.obtenerPersonDataByProgrammingLanguage("Java");
        personsValuesByLanguage.forEach(person -> {
            System.out.println(person[0] + " es experto en " + person[1]);
        });
        System.out.println("-".repeat(100));

        // Obtener uno solo objeto con findById
        Person person = personRepository.findById(2L).orElseThrow();
        System.out.println(person);
        System.out.println("-".repeat(100));

        Person thePerson = null;
        Optional<Person> personOptional = personRepository.findById(3L);
        if (personOptional.isPresent()) {
            thePerson = personOptional.get();
        }
        System.out.println(thePerson);
        System.out.println("-".repeat(100));

        personRepository.findById(4L).ifPresent(person1 -> System.out.println(person1));
        System.out.println("-".repeat(100));

        personRepository.findOne(5L).ifPresent(person1 -> System.out.println(person1));
        System.out.println("-".repeat(100));

        personRepository.findOneName("Pepe").ifPresent(person1 -> System.out.println(person1));
        System.out.println("-".repeat(100));

        personRepository.findOneLikeName("Jos").ifPresent(person1 -> System.out.println(person1));
        System.out.println("-".repeat(100));

        personRepository.findByNameContaining("And").ifPresent(person1 -> System.out.println(person1));
        System.out.println("-".repeat(100));

        create();
    }

    public void create() {
        Person person = new Person(null, "Carolina", "G", "Python");
        personRepository.save(person);
        List<Person> persons = (List<Person>) personRepository.findAll();
        persons.forEach(System.out::println);
        System.out.println("-".repeat(100));
    }
}
