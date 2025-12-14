package service.interfaces;

import enums.RoomType;

import java.util.Date;

public interface BookingService {
    void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut);
    void printAll();
}
