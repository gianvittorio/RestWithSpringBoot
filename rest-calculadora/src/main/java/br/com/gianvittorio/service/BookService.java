package br.com.gianvittorio.service;

import br.com.gianvittorio.converter.DozerConverter;
import br.com.gianvittorio.data.model.Book;
import br.com.gianvittorio.data.model.repository.BookRepository;
import br.com.gianvittorio.data.vo.v1.BookVO;
import br.com.gianvittorio.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public BookVO create(BookVO book) {
        Book entity = DozerConverter.parseObject(book, Book.class);

        BookVO vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public BookVO findById(Long id) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for id " + id));

        return DozerConverter.parseObject(entity, BookVO.class);
    }

    public List<BookVO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);
    }

    public BookVO update(BookVO book) {
        Book entity = repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No record found for such id"));
        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        BookVO vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public void delete(Long id) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for such id"));

        repository.delete(entity);
    }
}
