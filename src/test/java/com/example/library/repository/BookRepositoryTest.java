package com.example.library.repository;

import com.example.library.model.Author;
import com.example.library.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testFindAllBooksWithAuthors() {
        Author a = new Author();
        a.setName("Test Author");
        a.setEmail("test@test.com");
        authorRepository.save(a);

        Book b = new Book();
        b.setTitle("Test Book");
        b.setIsbn("123456");
        b.setAuthor(a);
        bookRepository.save(b);

        List<Book> books = bookRepository.findAllBooksWithAuthors();
        assertFalse(books.isEmpty());
        assertNotNull(books.get(0).getAuthor());
    }
}
