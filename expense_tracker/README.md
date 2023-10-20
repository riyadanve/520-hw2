# hw1- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 

## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTracker
```

You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.

## Open-Closed principle implementation
Modified the ExpenseTrackerModel class and Transaction class as described below to implement data encapsulation and data immutability.
This is usually done by keeping the data objects private, and getters and setters public.

1. ExpenseTrackerModel
- Changed the transaction list to private to ensure encapsulation of the transaction list.
- Modified the getTransactions method to return an unmodifiable list using unmodifiableList collection in java. This makes the list immutable when accessed through the getter method.

2. Transaction
- Changed the class' access modifier to final to prevent external data modification.
- Changed the fields amount, category, and timestamp to private for encapsulating the data.
- Removed the setter methods to prevent external modification of the fields, ensuring immutability. Only getter methods are included, which provide read only access to the data.

## Extensibility

Modified the ExpenseTrackerApp, ExpenseTrackerView and ExpenseTrackerController and added an interface TransactionFilter, AmountFilter and CategoryFilter to implement the functionality of adding a filter to the existing application.

1. Updated the ExpenseTrackerApp to add a seperate action listener for the apply filter button and display an error dialog box in case of invalid input values.
2. Added a seperate interface TransactionFilter and extended it into reusable classes: AmountFilter and CategoryFilter to ensure that new filters can be added easily.
3. Updated the ExpenseTrackerController code to add a new method applyfilter() which applies input validation on the filter values and calls the view function to display the highlighted rows.
4. Updated the ExpenseTracekrView with a highlightRows() function to highlight the rows which match the filter criteria in green. Updated the code for filter UI components.


