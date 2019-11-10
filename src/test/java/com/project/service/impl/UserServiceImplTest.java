package com.project.service.impl;

import com.project.dao.UserDao;
import com.project.domain.user.User;
import com.project.entity.user.Role;
import com.project.entity.user.UserEntity;
import com.project.exception.EmailAlreadyExistException;
import com.project.exception.InvalidLoginException;
import com.project.service.encoder.PasswordEncoder;
import com.project.service.mapper.UserMapper;
import com.project.service.validator.Validator;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private final User user = initUser();

    private final UserEntity entity = initEntity();

    @Mock
    private UserDao userDao;
    @Mock
    private Validator<User> userValidator;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserServiceImpl userService;

    @After
    public void resetMock() {
        reset(userDao, userValidator, passwordEncoder, mapper);
    }

    @Test
    public void loginShouldThrowInvalidLoginRuntimeExceptionNullEntity() {
        expectedException.expect(InvalidLoginException.class);
        expectedException.expectMessage("Invalid email or password");

        when(passwordEncoder.encode(anyString())).thenReturn("1");
        userService.login("s", "1");
    }

    @Test
    public void loginShouldThrowInvalidLoginRuntimeExceptionWithIncorrectPassword() {
        expectedException.expect(InvalidLoginException.class);
        expectedException.expectMessage("Invalid email or password");

        when(passwordEncoder.encode(anyString())).thenReturn("21");
        when(userDao.findByEmail(anyString())).thenReturn(Optional.of(entity));

        userService.login("mail", "22");
    }

    @Test
    public void loginShouldReturnUser() {
        when(passwordEncoder.encode("1111")).thenReturn("1111");
        when(userDao.findByEmail("user@gmail.com")).thenReturn(Optional.of(entity));
        when(mapper.mapUserEntityToUser(any(UserEntity.class))).thenReturn(user);
        User expected = user;

        User actual = userService.login("user@gmail.com", "1111");

        assertThat(actual, is(expected));
    }

    @Test
    public void registerShouldThrowEmailAlreadyExistException() {
        expectedException.expect(EmailAlreadyExistException.class);
        expectedException.expectMessage("User with such email already exists");
        when(userDao.findByEmail(anyString())).thenReturn(Optional.of(entity));

        userService.register(user);
    }

    @Test
    public void registerShouldReturnTrue() {
        when(passwordEncoder.encode("1111")).thenReturn("1111");
        when(userDao.findByEmail(anyString())).thenReturn(Optional.ofNullable(null));
        when(mapper.mapUserToUserEntity(any(User.class))).thenReturn(entity);
        when(userDao.save(entity)).thenReturn(true);

        Boolean actual = userService.register(user);

        assertEquals(true, actual);
    }

    @Test
    public void showAllShouldReturnList() {
        List<User> users = Collections.singletonList(user);
        List<UserEntity> entities = Collections.singletonList(entity);
        when(userDao.findAll(anyInt(), anyInt())).thenReturn(entities);
        when(mapper.mapUserEntityToUser(entity)).thenReturn(user);

        List<User> actual = userService.showAll(1, 1);

        assertThat(actual, is(users));
    }

    private UserEntity initEntity() {
        return UserEntity.builder()
                .withId(1L)
                .withName("Name")
                .withSurname("Surname")
                .withEmail("user@gmail.com")
                .withPassword("1111")
                .withRole(Role.USER)
                .build();
    }

    private User initUser() {
        return User.builder()
                .withId(1L)
                .withName("Name")
                .withSurname("Surname")
                .withEmail("user@gmail.com")
                .withPassword("1111")
                .withRole(Role.USER)
                .build();
    }

}