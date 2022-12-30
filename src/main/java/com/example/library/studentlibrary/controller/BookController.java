package com.example.library.studentlibrary.controller;

import com.example.library.studentlibrary.models.Author;
import com.example.library.studentlibrary.models.Book;
import com.example.library.studentlibrary.models.Genre;
import com.example.library.studentlibrary.services.AuthorService;
import com.example.library.studentlibrary.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Add required annotations
@RestController
public class BookController {

    @Autowired
    BookService bs;
    @PostMapping("/book/createBook")
    public ResponseEntity<String> addBook(@RequestBody Book b)
    {
        bs.createBook(b);
        return new ResponseEntity<>("book is successfully added" , HttpStatus.CREATED);
    }
    //Write createBook API with required annotations

    //Add required annotations
    @GetMapping("/book/getBooks")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(value = "genre", required = false) String genre,
                                               @RequestParam(value = "available", required = false, defaultValue = "false") boolean available,
                                               @RequestParam(value = "author", required = false) String author){

        List<Book> bookList = null; //find the elements of the list by yourself
        bookList = bs.getBooks(genre , available , author);
        return new ResponseEntity<>(bookList, HttpStatus.OK);

    }
}
