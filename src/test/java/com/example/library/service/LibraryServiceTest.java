package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LibraryServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryService libraryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAuthors() {
        Author a1 = new Author();
        a1.setName("Author 1");
        Author a2 = new Author();
        a2.setName("Author 2");

        when(authorRepository.findAll()).thenReturn(Arrays.asList(a1, a2));

        List<Author> authors = libraryService.getAllAuthors();
        assertEquals(2, authors.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void testSaveBook() {
        Book b = new Book();
        b.setTitle("Test Book");
        
        when(bookRepository.save(b)).thenReturn(b);

        Book savedBook = libraryService.saveBook(b);
        assertEquals("Test Book", savedBook.getTitle());
        verify(bookRepository, times(1)).save(b);
    }
}
