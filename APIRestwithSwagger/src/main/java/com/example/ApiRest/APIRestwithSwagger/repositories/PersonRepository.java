package com.example.ApiRest.APIRestwithSwagger.repositories;

import com.example.ApiRest.APIRestwithSwagger.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
