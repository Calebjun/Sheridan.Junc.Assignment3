package sheridan.junc.librarydata.controller;

import sheridan.junc.librarydata.data.Book;
import sheridan.junc.librarydata.data.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/books", produces = "application/json")
@Tag(name = "Books", description = "Endpoints for accessing library books")
public class BooksController {

    private final BookRepository bookRepository;

    public BooksController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    @Operation(summary = "Retrieve all books", description = "Returns a list of all books")
    public List<Book> getAllBooks() {
        log.trace("getAllBooks() is called");
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a book by ID", description = "Returns the book with the specified item ID")
    @Parameters(
            @Parameter(name = "id", description = "The item ID of the book", required = true, example = "BK-01")
    )
    public ResponseEntity<Book> getBookById(@PathVariable String id) throws NoResourceFoundException {
        log.trace("getBookById() is called with id={}", id);
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NoResourceFoundException(HttpMethod.GET, null, "/api/books/" + id));
    }
}
