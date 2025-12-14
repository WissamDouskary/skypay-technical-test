package service;

import entity.User;
import exception.BusinessException;
import service.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private List<User> userList = new ArrayList<>();

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

    @Override
    public void setUser(int userId, int balance) {
        for (User u : userList) {
            if (u.getId() == userId) {
                throw new BusinessException("User id already exist!");
            }
        }
        userList.add(new User(userId, balance));
    }

    public List<User> getUserList() {
        return userList;
    }
}
