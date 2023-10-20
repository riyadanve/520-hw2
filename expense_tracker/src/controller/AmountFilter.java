package controller;

import java.util.List;
import java.util.stream.Collectors;

import model.Transaction;

public class AmountFilter implements TransactionFilter {
	
	private double filterAmount;

	public AmountFilter(double filterAmount) {
        this.filterAmount = filterAmount;
    }

	@Override
	public List<Transaction> filter(List<Transaction> transactions) {
		return transactions.stream()
                .filter(transaction -> transaction.getAmount() == filterAmount)
                .collect(Collectors.toList());
	}

}
