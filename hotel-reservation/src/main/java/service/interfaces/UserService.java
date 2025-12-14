package service.interfaces;

import entity.User;

import java.util.List;

public interface UserService {
    void printAllUsers();
    void setUser(int userId, int balance);
    List<User> getUserList();
}
