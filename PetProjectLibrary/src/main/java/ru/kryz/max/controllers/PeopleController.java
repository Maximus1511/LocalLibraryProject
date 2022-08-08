package ru.kryz.max.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kryz.max.dao.PersonDAO;
import ru.kryz.max.models.Person;
import ru.kryz.max.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping("/showAll")
    public String showAllReaders(Model model){
        //get all people from DAO
        model.addAttribute("people", personDAO.showAll());
        return "people/showAll";
    }

    //page for add person
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    //add new person to db
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people/showAll";
    }

    //update exist person
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people/showAll";
    }

    //Show single person
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        //model.addAttribute("books", personDAO.getBooksByPersonId(id));

        return "people/show";
    }

}
