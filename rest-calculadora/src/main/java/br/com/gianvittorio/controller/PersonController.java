package br.com.gianvittorio.controller;

import br.com.gianvittorio.data.vo.v1.PersonVO;
import br.com.gianvittorio.data.vo.v2.PersonVOV2;
import br.com.gianvittorio.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping(
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO create(@RequestBody PersonVO person) {
        return personService.create(person);
    }

    @PostMapping("/v2")
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
        return personService.createV2(person);
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<PersonVO> findAll() {
        return personService.findAll();
    }

    @GetMapping(
            value = "/{id}",
            produces = {"application/json", "application/xml", "application/x-yaml"}
    )
    public PersonVO findById(@PathVariable("id") Long id) throws Exception {
        return personService.findById(id);
    }

    @PutMapping(
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"}
    )
    public PersonVO update(@RequestBody PersonVO person) {
        return personService.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        personService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
