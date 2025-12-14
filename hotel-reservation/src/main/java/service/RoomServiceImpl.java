package service;

import entity.Room;
import enums.RoomType;
import exception.BusinessException;
import service.interfaces.RoomService;

import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private List<Room> roomList = new ArrayList<>();

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
    public void printRooms() {
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
    }

    public List<Room> getRoomList() {
        return roomList;
    }
}
