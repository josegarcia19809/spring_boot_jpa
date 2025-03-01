package com.example.spring_boot_jpa.repositories;

import com.example.spring_boot_jpa.dto.PersonDTO;
import com.example.spring_boot_jpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByIdBetween(Long start, Long end);

    List<Person> findByNameBetween(String nameAfter, String nameBefore);
    
    // No incluye la P
    @Query("select p from Person p where p.name between ?1 and ?2")
    List<Person> findAllBetweenC1andC2(String c1, String c2);

    // Incluye el 5
    @Query("select p from Person p where p.id between ?1 and ?2")
    List<Person> findAllBetweenId1andId2(Integer id1, Integer id2);

    // No incluye la P
    @Query("select p from Person p where p.name between 'J' and 'P'")
    List<Person> findAllBetweenJP();

    // Incluye el 5
    @Query("select p from Person p where p.id between 2 and 5")
    List<Person> findAllBetween2and5();

    // @Query("select CONCAT( p.name, ' ', p.lastname) as fullname from Person p")
    @Query("select p.name || ' ' || p.lastname as fullname from Person p")
    List<String> findAllFullNameConcat();

    @Query("select upper(p.name || ' ' || p.lastname) as fullname from Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("select lower(p.name || ' ' || p.lastname) as fullname from Person p")
    List<String> findAllFullNameConcatLower();

    @Query("select count(distinct (p.programmingLanguage)) from Person p")
    Long findAllProgrammingLanguagesDistinctCount();

    @Query("select distinct (p.programmingLanguage) from Person p")
    List<String> findAllProgrammingLanguagesDistinct();

    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct (p.name) from Person p")
    List<String> findAllNamesDistinct();

    @Query("select new com.example.spring_boot_jpa.dto.PersonDTO(p.name, p.lastname) from Person p")
    List<PersonDTO> findAllPersonDto();

    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllObjectPersonPersonalized();

    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select CONCAT( p.name, ' ', p.lastname) as fullname from Person p where p.id=?1")
    String getFullNameById(Long id);

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

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonDataList();

    @Query("select p,  p.programmingLanguage from Person p")
    List<Object[]> findAllMixPersonDataList();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
    Optional<Object> obtenerPersonDataById(Long id);
}
