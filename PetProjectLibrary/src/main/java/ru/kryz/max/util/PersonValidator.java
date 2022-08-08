package ru.kryz.max.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kryz.max.dao.PersonDAO;
import ru.kryz.max.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target; //downcast to person because i know it exactly

        if (personDAO.getPersonByFullName(person.getFullName()).isPresent())
            errors.rejectValue("fullName", "", "Such Person already exists");
    }
}
