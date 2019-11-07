package com.project.context;

import com.project.dao.*;
import com.project.dao.impl.*;
import com.project.domain.user.User;
import com.project.service.UserService;
import com.project.service.encoder.PasswordEncoder;
import com.project.service.impl.UserServiceImpl;
import com.project.service.mapper.UserMapper;
import com.project.service.validator.UserValidator;
import com.project.service.validator.Validator;

public class ApplicationContextInjector {
    private static final DBConnector DB_CONNECTOR = new DBConnector("database");

    private static final UserDao USER_DAO = new UserDaoImpl(DB_CONNECTOR);

    private static final TicketDao TICKET_DAO = new TicketDaoImpl(DB_CONNECTOR);

    private static final PaymentDao PAYMENT_DAO = new PaymentDaoImpl(DB_CONNECTOR);

    private static final HallDao HALL_DAO = new HallDaoImpl(DB_CONNECTOR);

    private static final ExpositionDao EXPOSITION_DAO = new ExpositionDaoImpl(DB_CONNECTOR);

    private static final PasswordEncoder PASSWORD_ENCODER = new PasswordEncoder();

    private static final Validator<User> USER_VALIDATOR = new UserValidator();

    private static final UserMapper USER_MAPPER = new UserMapper();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_VALIDATOR, PASSWORD_ENCODER, USER_MAPPER);

    private static ApplicationContextInjector applicationContextInjector;

    private ApplicationContextInjector(){}

    public static ApplicationContextInjector getInstance(){
        if(applicationContextInjector == null){
            synchronized (ApplicationContextInjector.class){
                if(applicationContextInjector == null){
                    applicationContextInjector = new ApplicationContextInjector();
                }
            }
        }
        return applicationContextInjector;
    }

    public UserService getUserService(){
        return USER_SERVICE;
    }

}