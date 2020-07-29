package br.com.gianvittorio.controller;

import br.com.gianvittorio.data.vo.PersonVO;
import br.com.gianvittorio.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping
    public PersonVO create(@RequestBody PersonVO person) {
        return personService.create(person);
    }

    @GetMapping
    public List<PersonVO> findAll() {
        return personService.findAll();
    }

    @GetMapping(value = "/{id}")
    public PersonVO findById(@PathVariable("id") Long id) throws Exception {
        return personService.findById(id);
    }

    @PutMapping
    public PersonVO update(@RequestBody PersonVO person) {
        return personService.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        personService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
