package com.example.spring_boot_jpa.repositories;

import com.example.spring_boot_jpa.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
