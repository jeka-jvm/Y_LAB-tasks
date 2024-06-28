package org.coworking;

import org.coworking.dao.*;
import org.coworking.service.*;
import org.coworking.ui.ConsoleUI;


/**
 * Главный класс приложения для управления рабочими пространствами и конференц-залами.
 */
public class App {

    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        // Создание DAO для работы с пользователями, рабочими пространствами, конференц-залами и бронированиями
        UserDao userDao = new UserDaoImpl();
        WorkspaceDao workspaceDao = new WorkspaceDaoImpl();
        ConferenceRoomDao conferenceRoomDao = new ConferenceRoomDaoImpl();
        BookingDao bookingDao = new BookingDaoImpl();

        // Инициализация сервисов с передачей DAO
        UserService userService = new UserServiceImpl(userDao);
        WorkspaceService workspaceService = new WorkspaceServiceImpl(workspaceDao);
        ConferenceRoomService conferenceRoomService = new ConferenceRoomServiceImpl(conferenceRoomDao);
        BookingService bookingService = new BookingServiceImpl(bookingDao, workspaceDao, conferenceRoomDao);

        // Инициализация консольного интерфейса с передачей сервисов
        ConsoleUI consoleUI = new ConsoleUI(userService, workspaceService, conferenceRoomService, bookingService);

        // Запуск консольного интерфейса
        consoleUI.start();
    }
}

