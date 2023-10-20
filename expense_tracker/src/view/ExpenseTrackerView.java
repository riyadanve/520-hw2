package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;


import controller.InputValidation;
import controller.ExpenseTrackerController;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  private JButton filterBtn;
  private JComboBox<String> filterTypeComboBox;
  private JTextField filterValueField;
  

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(800, 600); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);
    
    
    JLabel filterTypeLabel = new JLabel("Filter by:");
    String[] filterTypes = {"None", "Amount", "Category"};
    filterTypeComboBox = new JComboBox<>(filterTypes);

    JLabel filterValueLabel = new JLabel("Filter Value:");
    filterValueField = new JTextField(10);
    
    filterBtn = new JButton("Apply Filter");

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new FlowLayout());
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
  
    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.EAST);
  
    // Set frame properties
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    
    JPanel filterPanel = new JPanel();
    filterPanel.setLayout(new FlowLayout());
    filterPanel.add(filterTypeLabel);
    filterPanel.add(filterTypeComboBox);
    filterPanel.add(filterValueLabel);
    filterPanel.add(filterValueField);
    filterPanel.add(filterBtn);

    add(filterPanel, BorderLayout.SOUTH);    
  
  }

  public void refreshTable(List<Transaction> transactions) {
	  
	  transactionsTable.clearSelection(); 
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
        
        //boolean shouldHighlight = false;
        }

        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);

      // Fire table update
      transactionsTable.updateUI();
  
    } 
  
  public void highlightRows(List<Transaction> transactions) {
	  
	  transactionsTable.clearSelection(); // Clear previous selections
	  
	    String filterType = filterTypeComboBox.getSelectedItem().toString();
	    String filterValue = filterValueField.getText();

	    for (int row = 0; row < transactionsTable.getRowCount(); row++) {
	        
	    	 if (row < transactions.size()) {
	    	Transaction t = transactions.get(row);
	    	
	    	System.out.println("Row: " + row);
            System.out.println("Transaction Amount: " + t.getAmount());
            System.out.println("Transaction Category: " + t.getCategory());
	        
	        if (filterType.equalsIgnoreCase("Amount")) {
	            double filterAmount = Double.parseDouble(filterValue);
	            if (t.getAmount() == filterAmount) {
	                transactionsTable.addRowSelectionInterval(row, row);
	                transactionsTable.setSelectionBackground(new Color(173, 255, 168));
	            }
	        } else if (filterType.equalsIgnoreCase("Category")) {
	            String filterCategory = filterValue;
	            if (t.getCategory().equalsIgnoreCase(filterCategory)) {
	                transactionsTable.addRowSelectionInterval(row, row);
	                transactionsTable.setSelectionBackground(new Color(173, 255, 168));
	            }
	        }
	        
	    }
	    }
  }
  

  
  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  
  public JButton filterBtn() {
	  return filterBtn;
  }
  
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
  
  public String getFilterType() {
      return filterTypeComboBox.getSelectedItem().toString();
  }

  public String getFilterValue() {
      return filterValueField.getText();
  }

  public void setController(ExpenseTrackerController controller) {
      filterBtn.addActionListener(e -> controller.applyFilter(getFilterType(), getFilterValue()));
  }
}
