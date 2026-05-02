package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    // --- Read Operations ---
    @GetMapping
    public String listEntities(Model model) {
        model.addAttribute("authors", libraryService.getAllAuthors());
        model.addAttribute("books", libraryService.getAllBooksWithAuthors());
        return "list";
    }

    // --- Create Operations ---
    @GetMapping("/addAuthor")
    public String showAddAuthorForm(Author author) {
        return "add-author";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@Valid Author author, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "add-author";
        }
        try {
            libraryService.saveAuthor(author);
            redirectAttributes.addFlashAttribute("successMessage", "Author added successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Integrity violation: Could not save author.");
        }
        return "redirect:/";
    }

    @GetMapping("/addBook")
    public String showAddBookForm(Book book, Model model) {
        model.addAttribute("authors", libraryService.getAllAuthors());
        return "add-book";
    }

    @PostMapping("/addBook")
    public String addBook(@Valid Book book, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "add-book";
        }
        try {
            libraryService.saveBook(book);
            redirectAttributes.addFlashAttribute("successMessage", "Book added successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Integrity violation: Could not save book.");
        }
        return "redirect:/";
    }

    // --- Update Operations ---
    @GetMapping("/editAuthor/{id}")
    public String showUpdateAuthorForm(@PathVariable("id") long id, Model model) {
        Author author = libraryService.getAuthorById(id);
        model.addAttribute("author", author);
        return "edit-author";
    }

    @PostMapping("/updateAuthor/{id}")
    public String updateAuthor(@PathVariable("id") long id, @Valid Author author,
                               BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            author.setId(id);
            return "edit-author";
        }
        try {
            libraryService.saveAuthor(author);
            redirectAttributes.addFlashAttribute("successMessage", "Author updated successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Integrity violation: Could not update author.");
        }
        return "redirect:/";
    }

    @GetMapping("/editBook/{id}")
    public String showUpdateBookForm(@PathVariable("id") long id, Model model) {
        Book book = libraryService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", libraryService.getAllAuthors());
        return "edit-book";
    }

    @PostMapping("/updateBook/{id}")
    public String updateBook(@PathVariable("id") long id, @Valid Book book,
                             BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            book.setId(id);
            model.addAttribute("authors", libraryService.getAllAuthors());
            return "edit-book";
        }
        try {
            libraryService.saveBook(book);
            redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Integrity violation: Could not update book.");
        }
        return "redirect:/";
    }
}
