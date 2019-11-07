package com.project.service.validator;

import com.project.domain.user.User;
import com.project.exception.InvalidRegistrationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserValidatorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private final UserValidator userValidator = new UserValidator();

    @Test
    public void validateShouldThrowInvalidRegistrationExceptionWithNullUser() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Invalid user");

        userValidator.validate(null);
    }

    @Test
    public void validateShouldThrowInvalidRegistrationExceptionWithInvalidName() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Name does not match regex");


        userValidator.validate(User.builder()
                .withName("SGH1")
                .build());
    }

    @Test
    public void validateShouldThrowInvalidRegistrationExceptionWithInvalidEmail() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Email does not match regex");

        userValidator.validate(User.builder()
                .withName("Name")
                .withEmail("12")
                .build());
    }

    @Test
    public void validateShouldThrowInvalidRegistrationExceptionWithInvalidPassword() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Password does not match regex");

        userValidator.validate(User.builder()
                .withName("Name")
                .withEmail("email@gmail.com")
                .withPassword("1")
                .build());
    }

    @Test
    public void validateShouldNotThrowException(){
        userValidator.validate(User.builder()
                .withName("Name")
                .withEmail("email@gmail.com")
                .withPassword("Pass111")
                .build());
    }

}