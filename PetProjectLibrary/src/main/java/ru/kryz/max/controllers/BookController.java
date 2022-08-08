package ru.kryz.max.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kryz.max.dao.BookDAO;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping("/showAll")
    public String showAll(Model model){
        model.addAttribute("books", bookDAO.showAll());
        return "books/showAll";
    }
}
