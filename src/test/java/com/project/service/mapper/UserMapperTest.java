package com.project.service.mapper;

import com.project.domain.User;
import com.project.entity.Role;
import com.project.entity.UserEntity;
import org.junit.Test;

import static com.project.MockData.MOCK_USER;
import static com.project.MockData.MOCK_USER_ENTITY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserMapperTest {

    private static final User USER = MOCK_USER;
    private static final UserEntity USER_ENTITY = MOCK_USER_ENTITY;

    private final UserMapper userMapper = new UserMapper();

    @Test
    public void mapUserEntityToUserShouldReturnUser() {
        final User actual = userMapper.mapUserEntityToUser(USER_ENTITY);

        assertThat(actual.getId(), is(MOCK_USER_ENTITY.getId()));
        assertThat(actual.getName(), is(MOCK_USER_ENTITY.getName()));
        assertThat(actual.getSurname(), is(MOCK_USER_ENTITY.getSurname()));
        assertThat(actual.getEmail(), is(MOCK_USER_ENTITY.getEmail()));
        assertThat(actual.getPassword(), is(MOCK_USER_ENTITY.getPassword()));
        assertThat(actual.getRole(), is(MOCK_USER_ENTITY.getRole()));
    }

    @Test
    public void mapUserToUserEntityShouldReturnUserEntity() {
        final UserEntity actual = userMapper.mapUserToUserEntity(USER);

        assertThat(actual.getId(), is(MOCK_USER.getId()));
        assertThat(actual.getName(), is(MOCK_USER.getName()));
        assertThat(actual.getSurname(), is(MOCK_USER.getSurname()));
        assertThat(actual.getEmail(), is(MOCK_USER.getEmail()));
        assertThat(actual.getPassword(), is(MOCK_USER.getPassword()));
        assertThat(actual.getRole(), is(MOCK_USER.getRole()));
    }

    private User initUser() {
        return User.builder()
                .withId(4L)
                .withName("Name")
                .withSurname("Surname")
                .withEmail("email@gmail.com")
                .withPassword("444111")
                .withRole(Role.USER)
                .build();
    }

    private UserEntity initUserEntity() {
        return UserEntity.builder()
                .withId(9L)
                .withName("Generic Name")
                .withSurname("Blevis")
                .withEmail("user@gmail.com")
                .withPassword("11117795")
                .withRole(Role.ADMIN)
                .build();
    }
}