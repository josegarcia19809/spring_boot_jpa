package com.example.spring_boot_jpa;

import com.example.spring_boot_jpa.entities.Person;
import com.example.spring_boot_jpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringBootJpaApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        personalizedQueries();
    }

    @Transactional(readOnly = true)
    public void personalizedQueries() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the id of the person: ");
        Long id = scanner.nextLong();

        String name = personRepository.getNameById(id);
        System.out.println(name);

        System.out.println("-".repeat(100));
        System.out.println("FullName: " + personRepository.getFullNameById(id));

        scanner.close();
    }

    @Transactional(readOnly = true)
    public void consultas() {
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
    }

    @Transactional
    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Last Name: ");
        String lastName = scanner.next();
        System.out.println("Programming Language: ");
        String programmingLanguage = scanner.next();
        scanner.close();

        Person person = new Person(null, name, lastName, programmingLanguage);
        personRepository.save(person);

        personRepository.findById(person.getId()).ifPresent(person1 -> System.out.println(person1));
        System.out.println("-".repeat(100));
    }

    @Transactional
    public void delete() {
        mostrarTodos();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id: ");
        Long id = scanner.nextLong();
        personRepository.deleteById(id);
        mostrarTodos();
        scanner.close();
    }

    @Transactional
    public void delete2() {
        mostrarTodos();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el id: ");
        Long id = scanner.nextLong();
        Optional<Person> personOptional = personRepository.findById(id);
        personOptional.ifPresentOrElse(person -> personRepository.delete(person),
                () -> {
                    System.out.println("No se encontro el id: " + id);
                }
        );

        mostrarTodos();
        scanner.close();
    }

    @Transactional
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id de la persona: ");
        Long id = scanner.nextLong();

        Optional<Person> personOptional = personRepository.findById(id);

        personOptional.ifPresentOrElse(person -> {
                    System.out.println(person);
                    System.out.println("Ingresa nuevo lenguaje de programación: ");
                    String programmingLanguage = scanner.next();
                    person.setProgrammingLanguage(programmingLanguage);
                    Person personDB = personRepository.save(person);
                    System.out.println(personDB);
                },
                () -> System.out.println("No existe la persona")
        );


        scanner.close();
    }

    @Transactional(readOnly = true)
    public void mostrarTodos() {
        System.out.println("-".repeat(100));
        personRepository.findAll().forEach(System.out::println);
        System.out.println("-".repeat(100));
    }
}


















