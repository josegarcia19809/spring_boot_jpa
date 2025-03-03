package com.example.spring_boot_jpa.repositories;

import com.example.spring_boot_jpa.dto.PersonDTO;
import com.example.spring_boot_jpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("select p from Person p where p.id=(select max(p.id) from Person p)")
    public Optional<Person> getLastRegistration();

    @Query("select p.name, length(p.name) from Person p where length(p.name) =" +
            "(select min(length(p.name)) from Person p)")
    public List<Object[]> getShorterNames();

    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    public Object getResumeAggregationFunction();

    @Query("select max(length(p.lastname)) from Person p")
    public Integer getMaxLengthLastname();

    @Query("select min(length(p.lastname)) from Person p")
    public Integer getMinLengthLastname();

    @Query("select p.name, length(p.name) from Person p")
    List<Object[]> getPersonNameLength();

    @Query("select max (p.programmingLanguage) from Person p")
    String maxProgrammingLanguage();

    @Query("select min (p.id) from Person p")
    Long minID();

    @Query("select count (p) from Person p")
    Long totalPersons();

    List<Person> findAllByOrderByProgrammingLanguageDescNameAsc();

    List<Person> findAllByOrderByProgrammingLanguageDesc();

    @Query("select p from Person p order by p.programmingLanguage desc")
    List<Person> findAllProgrammingLanguage();

    List<Person> findByIdBetweenOrderByIdDesc(Long start, Long end);

    List<Person> findByNameBetweenOrderByNameDesc(String nameAfter, String nameBefore);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name, p.lastname desc")
    List<Person> findAllBetweenC1andC2OrderByNameAndLastNameDesc(String c1, String c2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name")
    List<Person> findAllBetweenC1andC2OrderByName(String c1, String c2);

    // Incluye el 5
    @Query("select p from Person p where p.id between ?1 and ?2 order by p.name")
    List<Person> findAllBetweenId1andId2OrderByName(Integer id1, Integer id2);

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
