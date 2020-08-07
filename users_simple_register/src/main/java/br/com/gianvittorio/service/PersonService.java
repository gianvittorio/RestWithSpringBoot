package br.com.gianvittorio.service;

import br.com.gianvittorio.converter.DozerConverter;
import br.com.gianvittorio.converter.custom.PersonConverter;
import br.com.gianvittorio.data.vo.v1.PersonVO;
import br.com.gianvittorio.data.vo.v2.PersonVOV2;
import br.com.gianvittorio.exception.ResourceNotFoundException;
import br.com.gianvittorio.data.model.Person;
import br.com.gianvittorio.data.model.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonConverter converter;

    public PersonVO create(PersonVO person) {
        Person entity = DozerConverter.parseObject(person, Person.class);

        PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {
        Page<Person> page = repository.findPersonByName(firstName, pageable);
        return page.map(this::convertToPersonVO);
    }

    public PersonVO findById(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for id " + id));

        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public Page<PersonVO> findAll(Pageable pageable) {
        Page<Person> page = repository.findAll(pageable);

        return page.map(this::convertToPersonVO);
    }

    private PersonVO convertToPersonVO(Person entity) {
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public PersonVO update(PersonVO person) {
        Person entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No record found for such id"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    @Transactional
    public PersonVO disablePerson(Long id) {
        repository.disablePersons(id);

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for id " + id));

        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for such id"));

        repository.delete(entity);
    }
}
