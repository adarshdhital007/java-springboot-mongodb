//package com.adarsh.app.Rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api") // Base URL for all endpoints in this controller
//public class ApiControllers {
//
//    @Autowired
//    private DataRecordRepository dataRecordRepository;
//
//    // GET endpoint - returns a welcome message
//    @GetMapping("")
//    public String getPage() {
//        return "Welcome to the API!";
//    }
//
//    // GET endpoint - returns a message with a dynamic path variable
//    @GetMapping("/hello/{name}")
//    public String sayHello(@PathVariable String name) {
//        return "Hello, " + name + "!";
//    }
//
//    // GET endpoint - retrieves all added data from MongoDB
//    @GetMapping("/all")
//    public List<DataRecord> getAllData() {
//        return dataRecordRepository.findAll();  // Fetch all records from MongoDB
//    }
//
//    // POST endpoint - accepts data in request body, adds automatically incremented ID
//    @PostMapping("/add")
//    public String addData(@RequestBody DataRecord data) {
//        DataRecord savedData = dataRecordRepository.save(data);  // Save the new data record in MongoDB
//        return "Received data: " + savedData.toString();
//    }
//
//    // PUT endpoint - used for updating existing data
//    @PutMapping("/update/{id}")
//    public String updateData(@PathVariable String id, @RequestBody DataRecord newData) {
//        Optional<DataRecord> existingDataOpt = dataRecordRepository.findById(id);
//        if (existingDataOpt.isPresent()) {
//            DataRecord existingData = existingDataOpt.get();
//            existingData.setName(newData.getName());
//            existingData.setAge(newData.getAge());
//            dataRecordRepository.save(existingData);  // Save the updated record in MongoDB
//            return "Updated record with ID " + id + " to: " + existingData.toString();
//        } else {
//            return "ID not found.";
//        }
//    }
//
//    // DELETE endpoint - used for deleting data
//    @DeleteMapping("/delete/{id}")
//    public String deleteData(@PathVariable String id) {
//        Optional<DataRecord> existingDataOpt = dataRecordRepository.findById(id);
//        if (existingDataOpt.isPresent()) {
//            dataRecordRepository.deleteById(id);  // Delete the record by its ID from MongoDB
//            return "Deleted record with ID " + id;
//        } else {
//            return "ID not found.";
//        }
//    }
//}



package com.adarsh.app.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api") // Base URL for all endpoints in this controller
public class ApiControllers {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    // ADD an Author
    @PostMapping("/authors")
    public String addAuthor(@RequestBody Author author) {
        Author savedAuthor = authorRepository.save(author);
        return "Author added: " + savedAuthor.getName();
    }

    // ADD a Book
    @PostMapping("/books")
    public String addBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return "Book added: " + savedBook.getTitle();
    }

    // GET All Books
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();  // Fetch all books from MongoDB
    }

    // GET a Book by ID
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable String id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);  // Return book if found, otherwise null
    }

    // GET Books by Author ID
    @GetMapping("/books/author/{authorId}")
    public List<Book> getBooksByAuthorId(@PathVariable String authorId) {
        return bookRepository.findAll()
                             .stream()
                             .filter(book -> book.getAuthorId().equals(authorId))
                             .toList();
    }

    // DELETE a Book by ID
    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable String id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return "Deleted book with ID: " + id;
        }
        return "Book with ID " + id + " not found.";
    }

    // DELETE an Author (also deletes books associated with the author)
    @DeleteMapping("/authors/{id}")
    public String deleteAuthor(@PathVariable String id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            // Deleting all books by this author before deleting the author
            bookRepository.deleteAll(bookRepository.findAll()
                    .stream()
                    .filter(book -> book.getAuthorId().equals(id))
                    .toList());
            authorRepository.deleteById(id);
            return "Deleted author with ID: " + id;
        }
        return "Author with ID " + id + " not found.";
    }
}

