package org.coworking.ui;

import org.coworking.model.Booking;
import org.coworking.model.ConferenceRoom;
import org.coworking.model.Workspace;
import org.coworking.service.BookingService;
import org.coworking.service.ConferenceRoomService;
import org.coworking.service.UserService;
import org.coworking.service.WorkspaceService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


/**
 * Консольный интерфейс для управления бронированием рабочих пространств и конференц-залов.
 */
public class ConsoleUI {
    private final UserService userService;
    private final WorkspaceService workspaceService;
    private final ConferenceRoomService conferenceRoomService;
    private final BookingService bookingService;
    private final Scanner scanner;

    /**
     * Конструктор для инициализации консольного интерфейса.
     *
     * @param userService           сервис пользователей
     * @param workspaceService      сервис рабочих пространств
     * @param conferenceRoomService сервис конференц-залов
     * @param bookingService        сервис бронирования
     */
    public ConsoleUI(UserService userService, WorkspaceService workspaceService,
                     ConferenceRoomService conferenceRoomService, BookingService bookingService) {
        this.userService = userService;
        this.workspaceService = workspaceService;
        this.conferenceRoomService = conferenceRoomService;
        this.bookingService = bookingService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Запуск консольного интерфейса.
     */
    public void start() {
        System.out.println("Добро пожаловать в систему управления рабочими пространствами и конференц-залами!");
        while (true) {
            showMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    listAllWorkspaces();
                    break;
                case 4:
                    listAllConferenceRooms();
                    break;
                case 5:
                    showBookingMenu();
                    break;
                case 6:
                    addNewWorkspace();
                    break;
                case 7:
                    addNewConferenceRoom();
                    break;
                case 8:
                    cancelBooking();
                    break;
                case 9:
                    listAllBookings();
                    break;
                case 10:
                    logout();
                    break;
                case 0:
                    System.out.println("Выход из приложения. До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private void showMainMenu() {
        System.out.println("\nГлавное меню:");
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход");
        System.out.println("3. Список всех рабочих пространств");
        System.out.println("4. Список всех конференц-залов");
        System.out.println("5. Меню бронирования");
        System.out.println("6. Добавить новое рабочее пространство");
        System.out.println("7. Добавить новый конференц-зал");
        System.out.println("8. Отменить бронирование");
        System.out.println("9. Список всех бронирований");
        System.out.println("10. Выйти из системы");
        System.out.print("Введите ваш выбор: ");
    }

    private void showBookingMenu() {
        System.out.println("\nМеню бронирования:");
        System.out.println("1. Забронировать рабочее пространство");
        System.out.println("2. Забронировать конференц-зал");
        System.out.println("3. Список бронирований по дате");
        System.out.println("4. Мои бронирования");
        System.out.println("0. Вернуться в главное меню");
        System.out.print("Введите ваш выбор: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                bookWorkspace();
                break;
            case 2:
                bookConferenceRoom();
                break;
            case 3:
                listBookingsByDate();
                break;
            case 4:
                listMyBookings();
                break;
            case 0:
                return;
            default:
                System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
        }
    }

    private void registerUser() {
        System.out.println("\nРегистрация пользователя:");
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        try {
            userService.registerUser(username, password);
            System.out.println("Регистрация прошла успешно!");
        } catch (Exception e) {
            System.out.println("Ошибка при регистрации: " + e.getMessage());
        }
    }

    private void loginUser() {
        System.out.println("\nВход пользователя:");
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        try {
            userService.login(username, password);
            System.out.println("Вход выполнен успешно!");
        } catch (Exception e) {
            System.out.println("Ошибка входа: " + e.getMessage());
        }
    }

    private void logout() {
        System.out.println("Выход из системы...");
        System.exit(0);
    }

    private void listAllWorkspaces() {
        List<Workspace> workspaces = workspaceService.getAllWorkspaces();
        if (workspaces.isEmpty()) {
            System.out.println("Рабочие пространства не найдены.");
        } else {
            System.out.println("\nВсе рабочие пространства:");
            for (Workspace workspace : workspaces) {
                System.out.println(workspace.getId() + ". " + workspace.getName() + " - Доступность: " + workspace.isAvailable());
            }
        }
    }

    private void listAllConferenceRooms() {
        List<ConferenceRoom> conferenceRooms = conferenceRoomService.getAllConferenceRooms();
        if (conferenceRooms.isEmpty()) {
            System.out.println("Конференц-залы не найдены.");
        } else {
            System.out.println("\nВсе конференц-залы:");
            for (ConferenceRoom room : conferenceRooms) {
                System.out.println(room.getId() + ". " + room.getName() + " - Доступность: " + room.isAvailable());
            }
        }
    }

    private void addNewWorkspace() {
        System.out.println("\nДобавление нового рабочего пространства:");
        System.out.print("Введите название рабочего пространства: ");
        String name = scanner.nextLine();
        System.out.print("Доступно ли оно? (true/false): ");
        boolean available = scanner.nextBoolean();
        scanner.nextLine();

        workspaceService.addWorkspace(name, available);
        System.out.println("Рабочее пространство успешно добавлено.");
    }

    private void addNewConferenceRoom() {
        System.out.println("\nДобавление нового конференц-зала:");
        System.out.print("Введите название конференц-зала: ");
        String name = scanner.nextLine();
        System.out.print("Доступен ли он? (true/false): ");
        boolean available = scanner.nextBoolean();
        scanner.nextLine();

        conferenceRoomService.addConferenceRoom(name, available);
        System.out.println("Конференц-зал успешно добавлен.");
    }

    private void bookWorkspace() {
        System.out.println("\nБронирование рабочего пространства:");
        System.out.print("Введите ID рабочего пространства для бронирования: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите время начала бронирования (yyyy-MM-dd HH:mm): ");
        LocalDateTime startTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.print("Введите время окончания бронирования (yyyy-MM-dd HH:mm): ");
        LocalDateTime endTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        try {
            bookingService.bookResource(id, "Workspace", userService.getLoggedInUser(), startTime, endTime);
            System.out.println("Рабочее пространство успешно забронировано.");
        } catch (Exception e) {
            System.out.println("Ошибка при бронировании: " + e.getMessage());
        }
    }

    private void bookConferenceRoom() {
        System.out.println("\nБронирование конференц-зала:");
        System.out.print("Введите ID конференц-зала для бронирования: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите время начала бронирования (yyyy-MM-dd HH:mm): ");
        LocalDateTime startTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.print("Введите время окончания бронирования (yyyy-MM-dd HH:mm): ");
        LocalDateTime endTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        try {
            bookingService.bookResource(id, "ConferenceRoom", userService.getLoggedInUser(), startTime, endTime);
            System.out.println("Конференц-зал успешно забронирован.");
        } catch (Exception e) {
            System.out.println("Ошибка при бронировании: " + e.getMessage());
        }
    }

    private void listBookingsByDate() {
        System.out.println("\nСписок бронирований по дате:");
        System.out.print("Введите дату для просмотра бронирований (yyyy-MM-dd): ");
        LocalDateTime date = LocalDateTime.parse(scanner.nextLine() + "T00:00:00");
        List<Booking> bookings = bookingService.getBookingsByDate(date);
        if (bookings.isEmpty()) {
            System.out.println("Бронирования на эту дату не найдены.");
        } else {
            System.out.println("Бронирования на " + date.toLocalDate() + ":");
            for (Booking booking : bookings) {
                System.out.println("ID: " + booking.getId() + ", Ресурс: " + booking.getResourceType() +
                        ", Пользователь: " + booking.getUsername() + ", Начало: " + booking.getStartTime() +
                        ", Конец: " + booking.getEndTime());
            }
        }
    }

    private void listMyBookings() {
        System.out.println("\nМои бронирования:");
        String loggedInUser = userService.getLoggedInUser();
        List<Booking> bookings = bookingService.getBookingsByUsername(loggedInUser);
        if (bookings.isEmpty()) {
            System.out.println("У вас нет бронирований.");
        } else {
            System.out.println("Ваши бронирования:");
            for (Booking booking : bookings) {
                System.out.println("ID: " + booking.getId() + ", Ресурс: " + booking.getResourceType() +
                        ", Начало: " + booking.getStartTime() + ", Конец: " + booking.getEndTime());
            }
        }
    }

    private void listAllBookings() {
        System.out.println("\nСписок всех бронирований:");
        List<Booking> bookings = bookingService.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("Бронирования не найдены.");
        } else {
            System.out.println("Все бронирования:");
            for (Booking booking : bookings) {
                System.out.println("ID: " + booking.getId() + ", Ресурс: " + booking.getResourceType() +
                        ", Пользователь: " + booking.getUsername() + ", Начало: " + booking.getStartTime() +
                        ", Конец: " + booking.getEndTime());
            }
        }
    }

    private void cancelBooking() {
        System.out.println("\nОтмена бронирования:");
        System.out.print("Введите ID бронирования для отмены: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        try {
            bookingService.cancelBooking(bookingId);
            System.out.println("Бронирование успешно отменено.");
        } catch (Exception e) {
            System.out.println("Бронирование не найдено: " + e.getMessage());
        }
    }
}
