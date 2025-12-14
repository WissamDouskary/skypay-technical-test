package service;

import entity.Booking;
import entity.Room;
import entity.User;
import enums.RoomType;
import exception.BusinessException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingServiceImpl implements BookingService {
    private List<Room> roomList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private List<Booking> bookingList = new ArrayList<>();

    @Override
    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        for (Room r : roomList) {
            if (r.getRoomNumber() == roomNumber) {
                throw new BusinessException("This room number is already added!");
            }
        }
        roomList.add(new Room(roomNumber, roomType, roomPricePerNight));
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

        for (User u : userList) {
            if (u.getId() == userId) {
                user = u;
            }
        }
        for (Room r : roomList) {
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
        System.out.println("Rooms ========================");
        if (!roomList.isEmpty()) {
            for (int i = roomList.size() - 1; i >= 0; i--) {
                Room r = roomList.get(i);
                System.out.println("Room Number: " + r.getRoomNumber());
                System.out.println("Room Type: " + r.getRoomType());
                System.out.println("Room price Per Night: " + r.getPricePerNight());
                System.out.println("===============================");
            }
        } else {
            System.out.println("No Room Found!");
        }

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

    @Override
    public void setUser(int userId, int balance) {
        for (User u : userList) {
            if (u.getId() == userId) {
                throw new BusinessException("User id already exist!");
            }
        }
        userList.add(new User(userId, balance));
    }

    @Override
    public void printAllUsers() {
        System.out.println("Users ===================");
        if (!userList.isEmpty()) {
            for (int i = userList.size() - 1; i >= 0; i--) {
                User u = userList.get(i);
                System.out.println("ID: " +u.getId());
                System.out.println("Balance: " +u.getBalance());
            }
        }else{
            System.out.println("No User for this time!");
        }
    }
}
