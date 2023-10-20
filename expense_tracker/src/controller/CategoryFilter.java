package controller;

import model.Transaction;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryFilter implements TransactionFilter {
    private String filterCategory;

    public CategoryFilter(String filterCategory) {
        this.filterCategory = filterCategory;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getCategory().equalsIgnoreCase(filterCategory))
                .collect(Collectors.toList());
    }
}