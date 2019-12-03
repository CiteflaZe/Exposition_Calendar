package com.project.service.impl;

import com.project.dao.UserDao;
import com.project.domain.User;
import com.project.entity.UserEntity;
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

import java.util.List;
import java.util.Optional;

import static com.project.MockData.MOCK_USER;
import static com.project.MockData.MOCK_USER_ENTITY;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final User USER = MOCK_USER;
    private static final UserEntity USER_ENTITY = MOCK_USER_ENTITY;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private UserDao userDao;
    @Mock
    private Validator<User> userValidator;
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @After
    public void resetMock() {
        reset(userDao, userValidator, encoder, userMapper);
    }

    @Test
    public void loginShouldThrowInvalidLoginRuntimeExceptionNullEntity() {
        expectedException.expect(InvalidLoginException.class);
        expectedException.expectMessage("Invalid email or password");

        when(userDao.findByEmail(anyString())).thenReturn(Optional.empty());

        userService.login("s", "1");
    }

    @Test
    public void loginShouldThrowInvalidLoginRuntimeExceptionWithIncorrectPassword() {
        expectedException.expect(InvalidLoginException.class);
        expectedException.expectMessage("Invalid email or password");

        when(encoder.encode(anyString())).thenReturn("21");
        when(userDao.findByEmail(anyString())).thenReturn(Optional.of(USER_ENTITY));

        userService.login(USER.getEmail(), USER.getPassword());
    }

    @Test
    public void loginShouldReturnUser() {
        when(userDao.findByEmail(anyString())).thenReturn(Optional.of(USER_ENTITY));
        when(encoder.encode(MOCK_USER_ENTITY.getPassword())).thenReturn(MOCK_USER_ENTITY.getPassword());
        when(userMapper.mapUserEntityToUser(any(UserEntity.class))).thenReturn(USER);

        User actual = userService.login(USER.getEmail(), USER.getPassword());

        assertThat(actual.getName(), is(MOCK_USER_ENTITY.getName()));
        assertThat(actual.getSurname(), is(MOCK_USER_ENTITY.getSurname()));
        assertThat(actual.getEmail(), is(MOCK_USER_ENTITY.getEmail()));
        assertThat(actual.getPassword(), is(MOCK_USER_ENTITY.getPassword()));
    }

    @Test
    public void registerShouldThrowEmailAlreadyExistException() {
        expectedException.expect(EmailAlreadyExistException.class);
        expectedException.expectMessage("User with such email already exists");

        when(userDao.findByEmail(anyString())).thenReturn(Optional.of(USER_ENTITY));

        userService.register(USER);
    }

    @Test
    public void registerShouldReturnTrue() {
        when(encoder.encode(USER.getPassword())).thenReturn(USER.getPassword());
        when(userDao.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userMapper.mapUserToUserEntity(any(User.class))).thenReturn(USER_ENTITY);
        when(userDao.save(USER_ENTITY)).thenReturn(true);

        Boolean actual = userService.register(USER);

        verify(encoder).encode(anyString());
        verify(userDao).save(any(UserEntity.class));
        assertThat(actual, is(true));
    }

    @Test
    public void showAllShouldReturnEmptyList() {
        when(userDao.findAll(anyInt(), anyInt())).thenReturn(emptyList());

        List<User> actual = userService.showAll(1, 1);

        assertThat(actual, is(emptyList()));
    }

    @Test
    public void showAllShouldReturnList() {
        when(userDao.findAll(anyInt(), anyInt())).thenReturn(singletonList(USER_ENTITY));
        when(userMapper.mapUserEntityToUser(USER_ENTITY)).thenReturn(USER);

        List<User> actual = userService.showAll(1, 1);

        verify(userDao).findAll(anyInt(), anyInt());
        assertThat(actual, hasItem(USER));
    }

    @Test
    public void showEntriesAmountShouldReturnAmountOfRowsInDatabase() {
        when(userDao.countEntries()).thenReturn(5L);

        final Long actual = userService.showEntriesAmount();

        verify(userDao).countEntries();
        assertThat(actual, is(5L));
    }

}