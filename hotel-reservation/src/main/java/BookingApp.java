import enums.RoomType;
import exception.BusinessException;
import service.BookingServiceImpl;
import service.RoomServiceImpl;
import service.UserServiceImpl;
import service.interfaces.BookingService;
import service.interfaces.RoomService;
import service.interfaces.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static helper.TestHelper.runTest;

public class BookingApp {
    public static void main(String[] args) throws ParseException {
        RoomService roomService = new RoomServiceImpl();
        UserService userService = new UserServiceImpl();
        BookingService bookingService = new BookingServiceImpl(roomService, userService);

        roomService.setRoom(1, RoomType.STANDARD, 1000);
        roomService.setRoom(2, RoomType.JUNIOR, 2000);
        roomService.setRoom(3, RoomType.MASTER, 3000);

        userService.setUser(1, 5000);
        userService.setUser(2, 10000);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Date d1 = format.parse("30/06/2026");
        Date d2 = format.parse("07/07/2026");
        Date d3 = format.parse("08/07/2026");
        Date d4 = format.parse("09/07/2026");

        runTest("User 1 books Room 2 (7 nights)", () ->
                bookingService.bookRoom(1, 2, d1, d2)
        );

        runTest("User 1 books Room 2 with invalid dates", () ->
                bookingService.bookRoom(1, 2, d2, d1)
        );

        runTest("User 1 books Room 1 (1 night)", () ->
                bookingService.bookRoom(1, 1, d2, d3)
        );

        runTest("User 2 books Room 1 (2 nights)", () ->
                bookingService.bookRoom(2, 1, d2, d4)
        );

        runTest("User 2 books Room 3 (1 night)", () ->
                bookingService.bookRoom(2, 3, d2, d3)
        );

        runTest("Add Room 1 again", () ->
                roomService.setRoom(1, RoomType.MASTER, 10000)
        );

        bookingService.printAll();
        userService.printAllUsers();
    }
}
