package se.yrgo.bokklubben.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.yrgo.bokklubben.domain.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {
    private List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        books.add(new Book("Män som hatar kvinnor", "Stieg Larsson", "Deckare"));
        books.add(new Book("En helt vanlig familj", "Mattias Edvardsson", "Deckare"));
        books.add(new Book("Harry Potter och de vises sten", "J.K. Rowling", "Fantasy"));
        books.add(new Book("Mannen som fann sin skugga", "Stieg Larsson", "Deckare"));
        books.add(new Book("A Game of Thrones","George RR Martin", "Fantasy"));
        books.add(new Book("Nahids Förbannelse™","Sebastian Magnusson","Fantasy"));
        return books;

    }
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("greeting", "Välkommen till Bokklubben!");
        model.addAttribute("currentDate", LocalDate.now());
        return "home";
    }
    @GetMapping("/books")
    public String books(Model model) {
        List<Book> bookList = getBooks();
        model.addAttribute("books", bookList);
        return "booklist";
    }
    @GetMapping("/genre")
    public String genreFilter(@RequestParam(required = false) String type, Model model) {
        List<Book> allBooks = getBooks();
        List<Book> filteredBooks;

        if (type == null || type.isEmpty()) {
            filteredBooks = allBooks;
        } else {
            filteredBooks = allBooks.stream()
                    .filter(b -> b.getGenre().equalsIgnoreCase(type))
                    .collect(Collectors.toList());
        }

        model.addAttribute("books", filteredBooks);
        model.addAttribute("selectedType", type);
        return "genre";
    }
}
