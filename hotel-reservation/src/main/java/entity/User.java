package entity;

public class User {
    private int id;
    private double balance;

    public User(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setId(int id) {
        this.id = id;
    }
}