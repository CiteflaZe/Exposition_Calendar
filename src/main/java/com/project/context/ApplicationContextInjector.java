package com.project.context;

import com.project.command.Command;
import com.project.command.DefaultCommand;
import com.project.command.user.*;
import com.project.dao.*;
import com.project.dao.impl.*;
import com.project.domain.user.User;
import com.project.service.ExpositionService;
import com.project.service.PaymentService;
import com.project.service.TicketService;
import com.project.service.UserService;
import com.project.service.encoder.PasswordEncoder;
import com.project.service.impl.ExpositionServiceImpl;
import com.project.service.impl.PaymentServiceImpl;
import com.project.service.impl.TicketServiceImpl;
import com.project.service.impl.UserServiceImpl;
import com.project.service.mapper.*;
import com.project.service.validator.PaginationValidator;
import com.project.service.validator.UserValidator;
import com.project.service.validator.Validator;

import java.util.HashMap;
import java.util.Map;

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

    private static final TicketMapper TICKET_MAPPER = new TicketMapper();

    private static final PaymentMapper PAYMENT_MAPPER = new PaymentMapper();

    private static final HallMapper HALL_MAPPER = new HallMapper();

    private static final ExpositionMapper EXPOSITION_MAPPER = new ExpositionMapper();

    private static final PaginationValidator PAGINATION_VALIDATOR = new PaginationValidator();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_VALIDATOR, PASSWORD_ENCODER, USER_MAPPER);

    private static final ExpositionService EXPOSITION_SERVICE = new ExpositionServiceImpl(EXPOSITION_DAO, EXPOSITION_MAPPER);

    private static final PaymentService PAYMENT_SERVICE = new PaymentServiceImpl(PAYMENT_DAO, PAYMENT_MAPPER);

    private static final TicketService TICKET_SERVICE = new TicketServiceImpl(TICKET_DAO, TICKET_MAPPER);

    private static final LogInCommand LOGIN_COMMAND = new LogInCommand(USER_SERVICE);

    private static final LogOutCommand LOGOUT_COMMAND = new LogOutCommand();

    private static final RegisterCommand REGISTER_COMMAND = new RegisterCommand(USER_SERVICE);

    private static final ShowExpositionsCommand SHOW_EXPOSITIONS_COMMAND = new ShowExpositionsCommand(EXPOSITION_SERVICE);

    private static final ShowTicketsCommand SHOW_TICKETS_COMMAND = new ShowTicketsCommand(PAYMENT_SERVICE, TICKET_SERVICE);

    private static final DefaultCommand DEFAULT_COMMAND = new DefaultCommand();

    private static final Map<String, Command> USER_COMMAND_NAME_TO_COMMAND = mapUserCommand();

    private static ApplicationContextInjector applicationContextInjector;

    private ApplicationContextInjector() {
    }

    public static ApplicationContextInjector getInstance() {
        if (applicationContextInjector == null) {
            synchronized (ApplicationContextInjector.class) {
                if (applicationContextInjector == null) {
                    applicationContextInjector = new ApplicationContextInjector();
                }
            }
        }
        return applicationContextInjector;
    }

    private static Map<String, Command> mapUserCommand() {
        Map<String, Command> userCommandNameToCommand = new HashMap<>();
        userCommandNameToCommand.put("login", LOGIN_COMMAND);
        userCommandNameToCommand.put("logout", LOGOUT_COMMAND);
        userCommandNameToCommand.put("register", REGISTER_COMMAND);
        userCommandNameToCommand.put("default", DEFAULT_COMMAND);
        userCommandNameToCommand.put("showExpositions", SHOW_EXPOSITIONS_COMMAND);
        userCommandNameToCommand.put("showTickets", SHOW_TICKETS_COMMAND);

        return userCommandNameToCommand;
    }

    public PaginationValidator getPaginationValidator() {
        return PAGINATION_VALIDATOR;
    }

    public UserService getUserService() {
        return USER_SERVICE;
    }

    public ExpositionService getExpositionService() {
        return EXPOSITION_SERVICE;
    }

    public Map<String, Command> getUserCommands() {
        return USER_COMMAND_NAME_TO_COMMAND;
    }

    public PaymentService getPaymentService() {
        return PAYMENT_SERVICE;
    }

    public TicketService getTicketService() {
        return TICKET_SERVICE;
    }
}
