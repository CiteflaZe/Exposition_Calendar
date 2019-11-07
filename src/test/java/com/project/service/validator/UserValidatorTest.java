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
    public void validateShouldThrowInvalidRegistrationRuntimeExceptionWithNullUser() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Invalid user");

        userValidator.validate(null);
    }

    @Test
    public void validateShouldThrowInvalidRegistrationRuntimeExceptionWithInvalidName() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Invalid Name");


        userValidator.validate(User.builder()
                .withName("SGH1")
                .build());
    }

    @Test
    public void validateShouldThrowInvalidRegistrationRuntimeExceptionWithInvalidEmail() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Invalid Email");

        userValidator.validate(User.builder()
                .withName("Name")
                .withEmail("12")
                .build());
    }

    @Test
    public void validateShouldThrowInvalidRegistrationRuntimeExceptionWithInvalidPassword() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Invalid Password");

        userValidator.validate(User.builder()
                .withName("Name")
                .withEmail("email@gmail.com")
                .withPassword("1")
                .build());
    }

    @Test
    public void validateShouldNotThrowException(){
//        userValidator.validate();
    }

}