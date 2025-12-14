# SkyPay Technical Tests

This repository contains two technical test projects.

## 1. Banking Service
**Location:** `banking-app/`

### Summary
Create the core functions of a banking system: deposit money, withdraw money, and print transactions.

### Instructions
The project includes a class named `AccountServiceImpl` that implements the `AccountService` interface.

**Rules:**
*   You cannot change the public interface of the class.

**Desired Behaviour:**
The acceptance test should express the following behavior:
```text
Given a client makes a deposit of 1000
And a deposit of 2000
And a withdrawal of 500
When they print their bank statement
Then they would see
```

### Technical Requirements
*   **Exception Handling:** Handle exceptions whenever needed (invalid inputs, etc.).
*   **Performance:** Code should be efficient.
*   **Testing:** Test the code well.
*   **Data Structures:** Do not use repositories. Use `ArrayLists` and update them.
*   **Data Types:** Use `int` for money amounts to keep auxiliaries simple.
*   **Formatting:** Logic takes precedence over spacing/indentation in output.
*   **Simplicity:** When in doubt, go for the simplest solution.

---

## 2. Hotel Reservation System
**Location:** `hotel-reservation/`

### Summary
Implement a simplified Hotel Reservation System managing Rooms, Users, and Bookings.

### Entities

#### User
*   Defined by `balance`.

#### Room
*   Defined by `type` and `price` of booking per night.
*   Types: `standard`, `junior`, `master`.
*   Two rooms can have the same type but different prices per night.

#### Booking
*   Design and implement the Booking entity yourself.
*   Can have relations with other entities if needed, respecting technical requirements.

### Technical Requirements
*   **Booking Logic:**
    *   User can book if they have enough balance and the room is free for the period.
    *   Update user balance upon successful booking.
*   **Room Management:**
    *   `setRoom(...)` creates a room if it does not exist.
    *   `setRoom(...)` should *not* impact previously created bookings.
*   **User Management:**
    *   `setUser(...)` creates a user if they do not exist.
*   **Reporting:**
    *   `printAll()`: Print all rooms and bookings (with user/room info) from latest to oldest.
    *   `printAllUsers()`: Print all user data from latest to oldest.
*   **Constraints:**
    *   No repositories, update `ArrayLists`.
    *   Date checks: Consider only Year, Month, Day.
    *   Handle Exceptions (invalid inputs, etc.).

### Service Interfaces
The project is structured with three main service interfaces:

**BookingService**
```java
public interface BookingService {
    void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut);
    void printAll();
}
```

**RoomService**
```java
public interface RoomService {
    void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight);
    void printRooms();
    List<Room> getRoomList();
}
```

**UserService**
```java
public interface UserService {
    void setUser(int userId, int balance);
    void printAllUsers();
    List<User> getUserList();
}
```

### Test Case Scenario
1.  **Create 3 Rooms:**
    *   ID: 1, Type: standard, Price: 1000
    *   ID: 2, Type: junior, Price: 2000
    *   ID: 3, Type: master, Price: 3000
2.  **Create 2 Users:**
    *   ID: 1, Balance: 5000
    *   ID: 2, Balance: 10000
3.  **Booking Actions:**
    *   User 1 tries booking Room 2 (30/06/2026 - 07/07/2026) [7 nights]
    *   User 1 tries booking Room 2 (07/07/2026 - 30/06/2026) [Invalid Date Order]
    *   User 1 tries booking Room 1 (07/07/2026 - 08/07/2026) [1 night]
    *   User 2 tries booking Room 1 (07/07/2026 - 09/07/2026) [2 nights - Overlap?]
    *   User 2 tries booking Room 3 (07/07/2026 - 08/07/2026) [1 night]
4.  **Update Room:**
    *   `setRoom(1, master, 10000)`
5.  **Output:**
    *   Provide screenshots of `printAll(...)` and `printAllUsers(...)` results.