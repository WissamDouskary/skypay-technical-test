package banking.service;

import banking.Exception.BusinessException;
import banking.Entity.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService{
    private List<Transaction> transactionList = new ArrayList<>();
    private int balance = 0;

    private static final DateTimeFormatter RELEASE_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void deposit(int amount) {
        if(amount <= 0){
            throw new BusinessException("Amount should be greater than zero!");
        }
        balance += amount;
        transactionList.add(new Transaction(LocalDate.now(), amount, balance));
    }

    @Override
    public void withdraw(int amount){
        if(amount <= 0){
            throw new BusinessException("Amount should be greater than zero!");
        }
        if(amount > balance){
            throw new BusinessException("You dont' have enough money to withdraw!");
        }
        balance -= amount;
        transactionList.add(new Transaction(LocalDate.now(), -amount, balance));
    }

    @Override
    public void printStatement() {
        System.out.println("Date          ||  Amount  ||  Balance ");

        for (int i = transactionList.size() - 1; i >= 0; i--) {
            Transaction t = transactionList.get(i);
            String dateStr = t.getDate().format(RELEASE_DATE_FORMATTER);
            System.out.println(dateStr + "    || "+t.getAmount() + "     || " + t.getBalance());
        }
    }
}
