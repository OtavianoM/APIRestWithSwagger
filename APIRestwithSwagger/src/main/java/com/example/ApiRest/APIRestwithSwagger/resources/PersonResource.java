package com.example.ApiRest.APIRestwithSwagger.resources;

import com.example.ApiRest.APIRestwithSwagger.models.Person;
import com.example.ApiRest.APIRestwithSwagger.repositories.PersonRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Api
@RestController
@RequestMapping(path = "/persons")
public class PersonResource {
    private PersonRepository personRepository;

    public PersonResource(PersonRepository personRepository){
        super();
        this.personRepository = personRepository;
    }
    @ApiOperation("Cadastra pessoas, uma por vez.")
    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person){
        personRepository.save(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
    @ApiOperation("Consulta pessoas, retornando todas em uma lista")
    @GetMapping
    public ResponseEntity<List<Person>> getAll(){
        List<Person> persons = new ArrayList<>();
        persons = personRepository.findAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }
    @ApiOperation("Consulta pessoas, retornando de acordo com o ID passado")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Person>> getById(@PathVariable Integer id){
        Optional<Person> person;

        try {
            person = personRepository.findById(id);
            return new ResponseEntity<Optional<Person>>(person, HttpStatus.OK);
        }catch (NoSuchElementException nsee){
            return new ResponseEntity<Optional<Person>>(HttpStatus.NOT_FOUND);
        }
    }
    @ApiOperation("Deletando registro de acordo com o ID passado")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Person>> deleteById(@PathVariable Integer id){

        try {
            personRepository.deleteById(id);
            return new ResponseEntity<Optional<Person>>(HttpStatus.OK);
        }catch (NoSuchElementException nsee){
            return new ResponseEntity<Optional<Person>>(HttpStatus.NOT_FOUND);
        }
    }
    @ApiOperation("Atualizando registro de acordo com o ID passado")
    @PutMapping(value="/{id}")
    public ResponseEntity<Person> update(@PathVariable Integer id, @RequestBody Person newPerson){
        return personRepository.findById(id).map(person -> {
        person.setName(newPerson.getName());
        person.setAge(newPerson.getAge());
        Person personoUpdate = personRepository.save(person);
        return ResponseEntity.ok().body(personoUpdate);
        }).orElse(ResponseEntity.notFound().build());
    }


}
