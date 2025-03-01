package com.example.spring_boot_jpa;

import com.example.spring_boot_jpa.dto.PersonDTO;
import com.example.spring_boot_jpa.entities.Person;
import com.example.spring_boot_jpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
        personalizedQueriesBetween();
    }

    @Transactional(readOnly = true)
    public void personalizedQueriesBetween() {
        System.out.println("Consultas por rangos de id");
        List<Person> persons = personRepository.findAllBetween2and5();
        persons.forEach(System.out::println);
        System.out.println("-".repeat(100));

        System.out.println("Consultas por rangos de nombre");
        List<Person> personsJP = personRepository.findAllBetweenJP();
        personsJP.forEach(System.out::println);
        System.out.println("-".repeat(100));

        System.out.println("Consultas por rangos de id dados por el usuario");
        List<Person> personsId1andId2 = personRepository.findAllBetweenId1andId2(2, 6);
        personsId1andId2.forEach(System.out::println);
        System.out.println("-".repeat(100));

        System.out.println("Consultas por rangos de nombre");
        List<Person> personsC1andC2 = personRepository.findAllBetweenC1andC2("C", "N");
        personsC1andC2.forEach(System.out::println);
        System.out.println("-".repeat(100));

        System.out.println("Consultas por rangos de id dados por el usuario generados por JPA");
        List<Person> personsId1andId2L = personRepository.findByIdBetween(2L, 6L);
        personsId1andId2L.forEach(System.out::println);
        System.out.println("-".repeat(100));

        System.out.println("Consultas por rangos de nombre generados por JPA");
        List<Person> personsC1andC2N = personRepository.findByNameBetween("C", "N");
        personsC1andC2N.forEach(System.out::println);
        System.out.println("-".repeat(100));
    }

    @Transactional(readOnly = true)
    public void personalizedQueriesConcatUpperAndLowerCase() {
        System.out.println("Consultas de nombres y apellidos de personas");
        List<String> fullNames = personRepository.findAllFullNameConcat();
        fullNames.forEach(System.out::println);
        System.out.println("-".repeat(100));

        System.out.println("Consultas de nombres y apellidos MAYÚSCULAS de personas");
        List<String> fullNamesUpper = personRepository.findAllFullNameConcatUpper();
        fullNamesUpper.forEach(System.out::println);
        System.out.println("-".repeat(100));

        System.out.println("Consultas de nombres y apellidos minúsculas de personas");
        List<String> fullNamesLower = personRepository.findAllFullNameConcatLower();
        fullNamesLower.forEach(System.out::println);
        System.out.println("-".repeat(100));
    }

    @Transactional(readOnly = true)
    public void personalizedQueriesDistinct() {
        System.out.println("Consultas con nombres de personas");
        List<String> names = personRepository.findAllNames();
        names.forEach(System.out::println);
        System.out.println("-".repeat(100));

        System.out.println("Consultas con nombres únicos de personas");
        List<String> namesD = personRepository.findAllNamesDistinct();
        namesD.forEach(System.out::println);
        System.out.println("-".repeat(100));

        System.out.println("Consultas con nombres únicos de lenguajes de programación");
        List<String> namesLanguagesD = personRepository.findAllProgrammingLanguagesDistinct();
        namesLanguagesD.forEach(System.out::println);
        System.out.println("-".repeat(100));

        System.out.println("Cantidad de nombres únicos de lenguajes de programación");
        Long totalLanguages = personRepository.findAllProgrammingLanguagesDistinctCount();
        System.out.println(totalLanguages);
        System.out.println("-".repeat(100));
    }

    @Transactional(readOnly = true)
    public void personalizedQueries2() {
        System.out.println("-".repeat(100));
        System.out.println("Consulta por objeto persona y lenguaje de programación");
        List<Object[]> personasRegs = personRepository.findAllMixPersonDataList();
        personasRegs.forEach(reg -> {
            System.out.println("Programming Language: " + reg[1] + "\t\t\tPerson: " + reg[0]);
        });

        System.out.println("-".repeat(100));
        System.out.println("Consulta por objeto persona que devuelve una instancia personalizada");
        List<Person> personas = personRepository.findAllObjectPersonPersonalized();
        personas.forEach(System.out::println);

        System.out.println("-".repeat(100));
        System.out.println("Consulta que devuelve objeto DTO de una clase personalizada");
        List<PersonDTO> personasDTO = personRepository.findAllPersonDto();
        personasDTO.forEach(System.out::println);

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

        System.out.println("-".repeat(100));
        System.out.println("Consulta por datos personalizados por id");
        Optional<Object> optionalPersonReg = personRepository.obtenerPersonDataById(id);
        if (optionalPersonReg.isPresent()) {
            Object[] personReg = (Object[]) optionalPersonReg.orElseThrow();
            System.out.println("id=" + personReg[0] + ", name=" + personReg[1] + ", fullName=" +
                    personReg[2] + ", language=" + personReg[3]);
        }


        System.out.println("-".repeat(100));
        System.out.println("Consulta por campos personalizados lista");
        List<Object[]> regs = personRepository.obtenerPersonDataList();
        regs.forEach(reg -> System.out.println("id=" + reg[0] + ", name=" + reg[1] + ", fullName=" +
                reg[2] + ", language=" + reg[3]));

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


















