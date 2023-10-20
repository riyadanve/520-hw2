package controller;

import view.ExpenseTrackerView;

import java.util.List;



import model.ExpenseTrackerModel;
import model.Transaction;
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }
  
  public void refresh_filter() {

	    // Get transactions from model
	    List<Transaction> transactions = model.getTransactions();

	    // Pass to view
	    view.highlightRows(transactions);

	  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }
  
  // Other controller methods
  
  public boolean applyFilter(String filterType, String filterValue) {
      List<Transaction> filteredTransactions;
      TransactionFilter filter;

      if ("amount".equalsIgnoreCase(filterType) && InputValidation.isValidAmount(Double.parseDouble(filterValue))) {
          double amount = Double.parseDouble(filterValue);
          filter = new AmountFilter(amount);
          
      } else if ("category".equalsIgnoreCase(filterType) && InputValidation.isValidCategory(filterValue)) {
          filter = new CategoryFilter(filterValue);
          
      } else {
          // Invalid filter type
          return false;
      }

      filteredTransactions = filter.filter(model.getTransactions());
      //view.refreshTable(filteredTransactions);
      //refresh();
      //view.highlightRows(filteredTransactions);
      refresh_filter();
      return true;
  }
  
  
  
}