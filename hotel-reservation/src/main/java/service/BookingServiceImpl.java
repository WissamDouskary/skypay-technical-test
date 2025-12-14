package service;

import entity.Booking;
import entity.Room;
import entity.User;
import enums.RoomType;
import exception.BusinessException;
import service.interfaces.BookingService;
import service.interfaces.RoomService;
import service.interfaces.UserService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingServiceImpl implements BookingService {
    private List<Booking> bookingList = new ArrayList<>();
    private RoomServiceImpl roomService;
    private UserServiceImpl userService;

    public BookingServiceImpl(RoomService roomService, UserService userService){
        this.roomService = (RoomServiceImpl) roomService;
        this.userService = (UserServiceImpl) userService;
    }

    @Override
    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {

        if (!checkIn.before(checkOut)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

//        if (!checkIn.after(new Date())) {
//            throw new IllegalArgumentException("Start date must be in the future");
//        }

        for (Booking b : bookingList) {
            if (b.getRoom().getRoomNumber() == roomNumber) {
                if (checkIn.before(b.getEndDate()) && checkOut.after(b.getStartDate())) {
                    throw new BusinessException("Room already booked for this period");
                }
            }
        }

        LocalDate start = checkIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end   = checkOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long nights = ChronoUnit.DAYS.between(start, end);

        if (nights <= 0) {
            throw new BusinessException("Invalid booking duration");
        }

        User user = null;
        Room room = null;

        for (User u : userService.getUserList()) {
            if (u.getId() == userId) {
                user = u;
            }
        }
        for (Room r : roomService.getRoomList()) {
            if (r.getRoomNumber() == roomNumber) {
                room = r;
            }
        }

        if (user == null) {
            throw new BusinessException("User not found");
        }
        if (room == null) {
            throw new BusinessException("Room not found");
        }

        double totalPrice = nights * room.getPricePerNight();
        if (user.getBalance() < totalPrice) {
            throw new BusinessException("Insufficient balance");
        }
        user.setBalance(user.getBalance() - (int) totalPrice);

        bookingList.add(new Booking(user, room, checkIn, checkOut));
    }

    @Override
    public void printAll() {
        roomService.printRooms();

        System.out.println("Bookings =====================");
        if (!bookingList.isEmpty()) {
            for (int i = bookingList.size() - 1; i >= 0; i--) {
                Booking b = bookingList.get(i);
                LocalDate startDate = b.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate endDate = b.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                System.out.println("ID: " + b.getId());
                System.out.println("User id: " + b.getUser().getId());
                System.out.println("Room id: " + b.getRoom().getRoomNumber());
                System.out.println("Start Date: " + formatter.format(b.getStartDate()));
                System.out.println("End Date: " + formatter.format(b.getEndDate()));
                System.out.println("Nights: " + ChronoUnit.DAYS.between(startDate, endDate));
                System.out.println("==========================");
            }
        } else {
            System.out.println("No Booking for this time!");
        }
    }

}
