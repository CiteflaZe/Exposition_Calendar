package com.project.service.validator;

import com.project.domain.User;
import com.project.exception.InvalidRegistrationException;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements Validator<User> {
    private static final Logger LOGGER = Logger.getLogger(UserValidator.class);

    private static final String NAME_REGEX = "^[a-zA-Zа-яА-Яієї']{2,25}$";
    private static final String SURNAME_REGEX = "^[a-zA-Zа-яА-Яієї']{2,25}$";
    private static final String EMAIL_REGEX = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,4}$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,15}$";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    private static final Pattern SURNAME_PATTERN = Pattern.compile(SURNAME_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    @Override
    public void validate(User entity) {
        if(entity == null){
            LOGGER.warn("Invalid user");
            throw new InvalidRegistrationException("Invalid user");
        }

        validateName(entity.getName());
        validateSurname(entity.getSurname());
        validateEmail(entity.getEmail());
        validatePassword(entity.getPassword());
    }

    private void validateName(String name){
        validateParam(name, NAME_PATTERN, "Name does not match regex");
    }

    private void validateSurname(String surname){
        validateParam(surname, SURNAME_PATTERN, "SurName does not match regex");
    }

    private void validateEmail(String email){
        validateParam(email, EMAIL_PATTERN, "Email does not match regex");
    }

    private void validatePassword(String password){
        validateParam(password, PASSWORD_PATTERN, "Password does not match regex");
    }

    private void validateParam(String param, Pattern pattern, String message) {
        Matcher matcher = pattern.matcher(param);

        if (!matcher.find()) {
            LOGGER.warn(message);
            throw new InvalidRegistrationException(message);
        }
    }
}
