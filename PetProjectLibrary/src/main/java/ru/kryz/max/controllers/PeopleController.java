package ru.kryz.max.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kryz.max.dao.PersonDAO;

@Controller
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/showAll")
    public String showAllReaders(Model model){
        //get all people from DAO
        model.addAttribute("people", personDAO.showAll());
        return "people/showAll";
    }
}
