package service.interfaces;

import entity.Room;
import enums.RoomType;

import java.util.List;

public interface RoomService {
    void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight);
    void printRooms();
    List<Room> getRoomList();
}
