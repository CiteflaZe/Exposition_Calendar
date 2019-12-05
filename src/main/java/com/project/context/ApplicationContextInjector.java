package com.project.context;

import com.project.command.Command;
import com.project.command.DefaultCommand;
import com.project.command.admin.AddExpositionCommand;
import com.project.command.admin.AddHallCommand;
import com.project.command.admin.ExpositionFormCommand;
import com.project.command.admin.HallFormCommand;
import com.project.command.admin.ShowExpositionsAdminCommand;
import com.project.command.admin.ShowHallsCommand;
import com.project.command.admin.ShowUsersCommand;
import com.project.command.authentication.LogInCommand;
import com.project.command.authentication.LogInFormCommand;
import com.project.command.authentication.LogOutCommand;
import com.project.command.authentication.RegisterCommand;
import com.project.command.authentication.RegisterFormCommand;
import com.project.command.user.DownloadTicketsCommand;
import com.project.command.user.MakePayment;
import com.project.command.user.ProcessDateCommand;
import com.project.command.user.ProcessExpositionCommand;
import com.project.command.user.ShowExpositionsUserCommand;
import com.project.command.user.ShowTicketsCommand;
import com.project.dao.DBConnector;
import com.project.dao.ExpositionDao;
import com.project.dao.HallDao;
import com.project.dao.PaymentDao;
import com.project.dao.TicketDao;
import com.project.dao.UserDao;
import com.project.dao.impl.ExpositionDaoImpl;
import com.project.dao.impl.HallDaoImpl;
import com.project.dao.impl.PaymentDaoImpl;
import com.project.dao.impl.TicketDaoImpl;
import com.project.dao.impl.UserDaoImpl;
import com.project.domain.User;
import com.project.service.ExpositionService;
import com.project.service.HallService;
import com.project.service.PaymentService;
import com.project.service.TicketService;
import com.project.service.UserService;
import com.project.service.encoder.PasswordEncoder;
import com.project.service.impl.ExpositionServiceImpl;
import com.project.service.impl.HallServiceImpl;
import com.project.service.impl.PaymentServiceImpl;
import com.project.service.impl.TicketServiceImpl;
import com.project.service.impl.UserServiceImpl;
import com.project.service.mapper.ExpositionMapper;
import com.project.service.mapper.HallMapper;
import com.project.service.mapper.PaymentMapper;
import com.project.service.mapper.TicketMapper;
import com.project.service.mapper.UserMapper;
import com.project.service.helper.PDFCreator;
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

    private static final PDFCreator PDF_CREATOR = new PDFCreator();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_VALIDATOR, PASSWORD_ENCODER, USER_MAPPER);

    private static final ExpositionService EXPOSITION_SERVICE = new ExpositionServiceImpl(EXPOSITION_DAO, EXPOSITION_MAPPER);

    private static final PaymentService PAYMENT_SERVICE = new PaymentServiceImpl(PAYMENT_DAO, PAYMENT_MAPPER);

    private static final TicketService TICKET_SERVICE = new TicketServiceImpl(TICKET_DAO, TICKET_MAPPER);

    private static final HallService HALL_SERVICE = new HallServiceImpl(HALL_DAO, HALL_MAPPER);

    private static final LogInCommand LOGIN_COMMAND = new LogInCommand(USER_SERVICE);

    private static final LogOutCommand LOGOUT_COMMAND = new LogOutCommand();

    private static final RegisterCommand REGISTER_COMMAND = new RegisterCommand(USER_SERVICE);

    private static final ShowExpositionsUserCommand SHOW_EXPOSITIONS_USER_COMMAND = new ShowExpositionsUserCommand(EXPOSITION_SERVICE);

    private static final ShowTicketsCommand SHOW_TICKETS_COMMAND = new ShowTicketsCommand(PAYMENT_SERVICE, TICKET_SERVICE);

    private static final DownloadTicketsCommand DOWNLOAD_TICKETS_COMMAND = new DownloadTicketsCommand(TICKET_SERVICE, PDF_CREATOR);

    private static final ProcessExpositionCommand PROCESS_EXPOSITION_COMMAND = new ProcessExpositionCommand(EXPOSITION_SERVICE);

    private static final ProcessDateCommand PROCESS_DATE_COMMAND = new ProcessDateCommand();

    private static final MakePayment MAKE_PAYMENT = new MakePayment(PAYMENT_SERVICE, TICKET_SERVICE);

    private static final ExpositionFormCommand EXPOSITION_FORM_COMMAND = new ExpositionFormCommand(HALL_SERVICE);

    private static final AddExpositionCommand ADD_EXPOSITION_COMMAND = new AddExpositionCommand(EXPOSITION_SERVICE);

    private static final ShowUsersCommand SHOW_USERS_COMMAND = new ShowUsersCommand(USER_SERVICE);

    private static final HallFormCommand HALL_FORM_COMMAND = new HallFormCommand();

    private static final AddHallCommand ADD_HALL_COMMAND = new AddHallCommand(HALL_SERVICE);

    private static final ShowHallsCommand SHOW_HALLS_COMMAND = new ShowHallsCommand(HALL_SERVICE);

    private static final LogInFormCommand LOGIN_FORM_COMMAND = new LogInFormCommand();

    private static final RegisterFormCommand REGISTER_FORM_COMMAND = new RegisterFormCommand();

    private static final ShowExpositionsAdminCommand SHOW_EXPOSITIONS_ADMIN_COMMAND = new ShowExpositionsAdminCommand(EXPOSITION_SERVICE);

    private static final DefaultCommand DEFAULT_COMMAND = new DefaultCommand();

    private static final Map<String, Command> AUTHENTICATION_COMMAND_NAME_TO_COMMAND = mapAuthenticationCommand();

    private static final Map<String, Command> USER_COMMAND_NAME_TO_COMMAND = mapUserCommand();

    private static final Map<String, Command> ADMIN_COMMAND_NAME_TO_COMMAND = mapAdminCommand();

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

    private static Map<String, Command> mapAuthenticationCommand() {
        Map<String, Command> authenticationCommandNameToCommand = new HashMap<>();
        authenticationCommandNameToCommand.put("login", LOGIN_COMMAND);
        authenticationCommandNameToCommand.put("register", REGISTER_COMMAND);
        authenticationCommandNameToCommand.put("logout", LOGOUT_COMMAND);
        authenticationCommandNameToCommand.put("loginForm", LOGIN_FORM_COMMAND);
        authenticationCommandNameToCommand.put("registerForm", REGISTER_FORM_COMMAND);
        authenticationCommandNameToCommand.put("default", DEFAULT_COMMAND);

        return authenticationCommandNameToCommand;
    }

    private static Map<String, Command> mapUserCommand() {
        Map<String, Command> userCommandNameToCommand = new HashMap<>();
        userCommandNameToCommand.put("showExpositions", SHOW_EXPOSITIONS_USER_COMMAND);
        userCommandNameToCommand.put("showTickets", SHOW_TICKETS_COMMAND);
        userCommandNameToCommand.put("download", DOWNLOAD_TICKETS_COMMAND);
        userCommandNameToCommand.put("processExposition", PROCESS_EXPOSITION_COMMAND);
        userCommandNameToCommand.put("processDate", PROCESS_DATE_COMMAND);
        userCommandNameToCommand.put("makePayment", MAKE_PAYMENT);

        return userCommandNameToCommand;
    }

    private static Map<String, Command> mapAdminCommand() {
        Map<String, Command> adminCommandNameToCommand = new HashMap<>();

        adminCommandNameToCommand.put("expositionForm", EXPOSITION_FORM_COMMAND);
        adminCommandNameToCommand.put("addExposition", ADD_EXPOSITION_COMMAND);
        adminCommandNameToCommand.put("showUsers", SHOW_USERS_COMMAND);
        adminCommandNameToCommand.put("hallForm", HALL_FORM_COMMAND);
        adminCommandNameToCommand.put("addHall", ADD_HALL_COMMAND);
        adminCommandNameToCommand.put("showHalls", SHOW_HALLS_COMMAND);
        adminCommandNameToCommand.put("showExpositions", SHOW_EXPOSITIONS_ADMIN_COMMAND);
        adminCommandNameToCommand.put("logout", LOGOUT_COMMAND);

        return adminCommandNameToCommand;
    }

    public Map<String, Command> getAuthenticationCommands() {
        return AUTHENTICATION_COMMAND_NAME_TO_COMMAND;
    }

    public Map<String, Command> getUserCommands() {
        return USER_COMMAND_NAME_TO_COMMAND;
    }

    public Map<String, Command> getAdminCommands() {
        return ADMIN_COMMAND_NAME_TO_COMMAND;
    }

    public DefaultCommand getDefaultCommand() {
        return DEFAULT_COMMAND;
    }
}
