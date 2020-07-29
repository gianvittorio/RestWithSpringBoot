package br.com.gianvittorio.service;

import br.com.gianvittorio.exception.ResourceNotFoundException;
import br.com.gianvittorio.model.Person;
import br.com.gianvittorio.model.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private PersonRepository repository;

    public Person create(Person person) {
        return repository.save(person);
    }

    public Person update(Person person) {
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No record found for such id"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for such id"));

        repository.delete(entity);
    }

    public Person findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for id " + id));
    }

    public List<Person> findAll() {
        return repository.findAll();
    }
}
