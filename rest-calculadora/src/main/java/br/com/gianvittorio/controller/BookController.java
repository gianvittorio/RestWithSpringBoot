package br.com.gianvittorio.controller;

import br.com.gianvittorio.data.vo.v1.BookVO;
import br.com.gianvittorio.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<BookVO> findAll() {
        List<BookVO> people = bookService.findAll();
        people.forEach(p -> {
            try {
                p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel());
            } catch (Exception e) {
            }
        });

        return people;
    }

    @GetMapping(
            value = "/{id}",
            produces = {"application/json", "application/xml", "application/x-yaml"}
    )
    public BookVO findById(@PathVariable("id") Long id) throws Exception {
        BookVO bookVO = bookService.findById(id);
        bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookVO;
    }

    @PostMapping(
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public BookVO create(@RequestBody BookVO book) throws Exception {
        BookVO bookVO = bookService.create(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @PutMapping(
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"}
    )
    public BookVO update(@RequestBody BookVO book) throws Exception {
        BookVO bookVO = bookService.update(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        bookService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
