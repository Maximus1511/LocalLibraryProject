package ru.kryz.max.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.kryz.max.models.Book;
import ru.kryz.max.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

   /* public List<Book> showAll() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }*/

    /*public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    } */

    /*public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES(?, ?, ?)", book.getTitle(),
                book.getAuthor(), book.getYear());
    }*/

    /*public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?", updatedBook.getTitle(),
                updatedBook.getAuthor(), updatedBook.getYear(), id);
    }*/

   /* public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }*/

    // Join book table and person table, get person with this book id
    public Optional<Person> getBookOwner(int id) {
        // choose all columns of person table from "joined table"
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
                        "WHERE Book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    //assign this book to person. This method realised when person take book from library
    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", selectedPerson.getId(), id);
    }

    // Person return book to library
    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", id);
    }
}
