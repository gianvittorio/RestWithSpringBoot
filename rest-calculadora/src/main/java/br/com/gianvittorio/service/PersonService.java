package br.com.gianvittorio.service;

import br.com.gianvittorio.model.Person;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonService {
    private final AtomicLong cnt = new AtomicLong();

    public Person create(Person person) {
        return person;
    }

    public Person update(Person person) {
        return person;
    }

    public void delete(String id) {

    }

    public Person findById(String id) {
        Person person = new Person();
        person.setId(cnt.incrementAndGet());
        person.setFirstName("Leandro");
        person.setLastName("Costa");
        person.setAddress("Uberlandia - Minas Gerais");
        person.setGender("Male");
        return person;
    }

    public List<Person> findAll() {
        return Stream.iterate(0, i -> i + 1)
                .limit(8)
                .map(i -> MockPerson(i))
                .collect(Collectors.toList());
    }

    private Person MockPerson(Integer i) {
        Person person = new Person();
        person.setId(cnt.incrementAndGet());
        person.setFirstName("First Name");
        person.setLastName("Last Name");
        person.setAddress("Address " + i);
        person.setGender("Gender");

        return person;
    }
}
