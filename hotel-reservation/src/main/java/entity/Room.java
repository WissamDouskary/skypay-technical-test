package entity;

import enums.RoomType;

public class Room {
    private final int roomNumber;
    private final RoomType roomType;
    private final double pricePerNight;

    public Room(int roomNumber, RoomType roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }

    public RoomType getRoomType() {
        return roomType;
    }
    public double getPricePerNight() {
        return pricePerNight;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
}
