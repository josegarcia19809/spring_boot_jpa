package com.example.spring_boot_jpa.repositories;

import com.example.spring_boot_jpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name);

    List<Person> findByProgrammingLanguage(String language);

    @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Person> buscarByProgrammingLanguage(String language, String name);

    List<Person> findByProgrammingLanguageAndName(String language, String name);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Object[]> obtenerPersonData(String language, String name);

    @Query("select p.name, p.programmingLanguage from Person p where p.name=?1")
    List<Object[]> obtenerPersonDataByName(String name);

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1")
    List<Object[]> obtenerPersonDataByProgrammingLanguage(String language);
}
