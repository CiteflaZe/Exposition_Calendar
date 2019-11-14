package com.project.service.mapper;

import com.project.domain.user.User;
import com.project.entity.user.Role;
import com.project.entity.user.UserEntity;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserMapperTest {

    private UserMapper mapper = new UserMapper();

    private final User user = initUser();

    private final UserEntity userEntity = initUserEntity();

    @Test
    public void mapUserEntityToUserShouldReturnUser() {
        final User actual = mapper.mapUserEntityToUser(userEntity);

        assertThat(actual.getId(), is(userEntity.getId()));
        assertThat(actual.getName(), is(userEntity.getName()));
        assertThat(actual.getSurname(), is(userEntity.getSurname()));
        assertThat(actual.getEmail(), is(userEntity.getEmail()));
        assertThat(actual.getPassword(), is(userEntity.getPassword()));
        assertThat(actual.getRole(), is(userEntity.getRole()));
    }

    @Test
    public void mapUserToUserEntityShouldReturnUserEntity() {
        final UserEntity actual = mapper.mapUserToUserEntity(user);

        assertThat(actual.getId(), is(user.getId()));
        assertThat(actual.getName(), is(user.getName()));
        assertThat(actual.getSurname(), is(user.getSurname()));
        assertThat(actual.getEmail(), is(user.getEmail()));
        assertThat(actual.getPassword(), is(user.getPassword()));
        assertThat(actual.getRole(), is(user.getRole()));
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