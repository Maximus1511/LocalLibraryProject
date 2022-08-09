package ru.kryz.max.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kryz.max.models.Book;
import ru.kryz.max.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository bookRepository) {
        this.booksRepository = bookRepository;
    }

    public List<Book> findAll() {
            return booksRepository.findAll();
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = booksRepository.findById(id).get();
        //add new book, there was no this book in Persistence context
        updatedBook.setId(id);
        updatedBook.setOwner(bookToBeUpdated.getOwner());

        booksRepository.save(updatedBook);
    }

}
