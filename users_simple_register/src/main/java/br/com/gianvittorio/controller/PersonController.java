package br.com.gianvittorio.controller;

import br.com.gianvittorio.data.vo.v1.PersonVO;
import br.com.gianvittorio.data.vo.v2.PersonVOV2;
import br.com.gianvittorio.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/person/v1")
//@CrossOrigin
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private PagedResourcesAssembler<PersonVO> assembler;

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        Map<String, Sort.Direction> directions = new HashMap<>();
        directions.put("asc", Sort.Direction.ASC);
        directions.put("desc", Sort.Direction.DESC);
        Sort.Direction sortDirection = (directions.containsKey(direction.toLowerCase()))
                ? (directions.get(direction))
                : (Sort.Direction.ASC);

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> people = personService.findAll(pageable);
        people.forEach(p -> {
            try {
                p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
            } catch (Exception e) {
            }
        });

        PagedModel<?> resources = assembler.toModel(people);

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping(value = "/findPersonByName/{firstName}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findPersonByName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        Map<String, Sort.Direction> directions = new HashMap<>();
        directions.put("asc", Sort.Direction.ASC);
        directions.put("desc", Sort.Direction.DESC);
        Sort.Direction sortDirection = (directions.containsKey(direction.toLowerCase()))
                ? (directions.get(direction))
                : (Sort.Direction.ASC);

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> people = personService.findPersonByName(firstName, pageable);
        people.forEach(p -> {
            try {
                p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
            } catch (Exception e) {
            }
        });

        PagedModel<?> resources = assembler.toModel(people);

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}",
            produces = {"application/json", "application/xml", "application/x-yaml"}
    )
    public PersonVO findById(@PathVariable("id") Long id) throws Exception {
        PersonVO personVO = personService.findById(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

    @PostMapping(
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PersonVO create(@RequestBody PersonVO person) throws Exception {
        PersonVO personVO = personService.create(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return personVO;
    }

    @PutMapping(
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"}
    )
    public PersonVO update(@RequestBody PersonVO person) throws Exception {
        PersonVO personVO = personService.update(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return personVO;
    }

    @PatchMapping(
            value = "/{id}",
            produces = {"application/json", "application/xml", "application/x-yaml"}
    )
    public PersonVO disablePerson(@PathVariable("id") Long id) throws Exception {
        PersonVO personVO = personService.disablePerson(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        personService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
