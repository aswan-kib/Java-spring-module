package org.example.entity;

import java.time.LocalDate;
import java.util.*;

import static java.util.Objects.nonNull;
import static org.example.Helper.formatDate;
import static org.example.Helper.isNotEmpty;

/**
 * @author aswan-kib
 * @since 12/8/25
 */
public class Transaction {

    private static final String INCOME = "INCOME";
    private static final String EXPENSE = "EXPENSE";
    private static final Set<String> TRANSACTION_TYPE = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(INCOME, EXPENSE)));

    private String id;
    private String type;
    private Double amount;
    private String description;
    private LocalDate date;

    public Transaction(String id, String type, Double amount, String description, LocalDate date) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    public void updateTransaction(Transaction transaction) {
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isIncomeType() {
        return this.type.equals(INCOME);
    }

    public boolean isExpenseType() {
        return this.type.equals(EXPENSE);
    }

    public boolean isValid() {
        boolean isValidType = isValidType();
        boolean isValidAmount = isValidAmount();
        boolean isValidDate = isValidDate();

        return isValidAmount && isValidDate && isValidType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Transaction that = (Transaction) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("ID: %s   ,Type: %s   , Amount: %.2f   , Date: %s   , Description: %s",
                id, type, amount, formatDate(date), description);
    }

    private boolean isValidAmount() {
        return isValid(nonNull(this.amount) && this.amount > 0, "Invalid amount");
    }

    private boolean isValidType() {
        return isValid(isNotEmpty(this.type) && TRANSACTION_TYPE.contains(this.type),
                "Invalid type");
    }

    private boolean isValidDate() {
        return isValid(nonNull(this.date), "Invalid date");
    }


    private boolean isValid(boolean isValid, String message) {
        if (!isValid) {
            System.out.println(message);
        }

        return isValid;
    }
}
