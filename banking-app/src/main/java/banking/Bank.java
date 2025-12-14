package banking;

import banking.service.AccountServiceImpl;

public class Bank {
    public static void main(String[] args) {
        AccountServiceImpl ac = new AccountServiceImpl();

        ac.deposit(1000);
        ac.deposit(2000);
        ac.withdraw(500);
        ac.printStatement();
    }
}
