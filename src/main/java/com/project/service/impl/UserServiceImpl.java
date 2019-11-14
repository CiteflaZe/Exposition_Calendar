package com.project.service.impl;

import com.project.dao.UserDao;
import com.project.domain.user.User;
import com.project.entity.user.UserEntity;
import com.project.exception.EmailAlreadyExistException;
import com.project.exception.InvalidLoginException;
import com.project.service.UserService;
import com.project.service.encoder.PasswordEncoder;
import com.project.service.mapper.UserMapper;
import com.project.service.validator.Validator;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private final UserDao userDao;
    private final Validator<User> userValidator;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    public UserServiceImpl(UserDao userDao, Validator<User> userValidator,
                           PasswordEncoder passwordEncoder, UserMapper mapper) {
        this.userDao = userDao;
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public User login(String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Optional<UserEntity> entity = userDao.findByEmail(email);
        //TODO rewrite to full Optional

        if (!entity.isPresent()) {
            LOGGER.warn("No such email");
            throw new InvalidLoginException("Invalid email or password");
        } else {
            if (entity.get().getPassword().equals(encodedPassword)) {
                return mapper.mapUserEntityToUser(entity.get());
            } else {
                LOGGER.warn("Incorrect password");
                throw new InvalidLoginException("Invalid email or password");
            }
        }
    }

    @Override
    public boolean register(User user) {
        userValidator.validate(user);

        if (userDao.findByEmail(user.getEmail()).isPresent()) {
            LOGGER.warn("User with such email already exists");
            throw new EmailAlreadyExistException("User with such email already exists");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User userWithEncodedPassword = new User(user, encodedPassword);
        UserEntity entity = mapper.mapUserToUserEntity(userWithEncodedPassword);

        return userDao.save(entity);
    }

    @Override
    public List<User> showAll(Integer startFrom, Integer rowCount) {

        List<UserEntity> userEntities = userDao.findAll( startFrom, rowCount);
        return userEntities.stream()
                .map(mapper::mapUserEntityToUser)
                .collect(Collectors.toList());
    }

    @Override
    public Integer showEntriesAmount() {
        return userDao.countEntries();
    }

}
