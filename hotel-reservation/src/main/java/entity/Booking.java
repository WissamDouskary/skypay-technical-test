package entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Booking {
    private String id = UUID.randomUUID().toString();
    private User user;
    private Room room;
    private Date startDate;
    private Date endDate;

    public Booking(User user, Room room, Date startDate, Date endDate) {
        this.user = user;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getId(){
        return id;
    }
}
