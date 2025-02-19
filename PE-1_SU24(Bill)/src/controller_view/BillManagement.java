/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller_view;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.function.Predicate;
import model.Bill;
import model.ListBill;

/**
 *
 * @author ADMIN
 */
public class BillManagement extends Menu {

    private ListBill BillList;

    // Display all bills
    public void DisplayBills() {
        BillList.printBills();
    }

    public BillManagement() throws ParseException {
        super("BILL MANAGEMENT SYSTEM", new String[]{
            "Display all bills",
            "Add new bill",
            "Delete a bill",
            "Sort a bill",
            "Search a bill",
            "Largest amount bill",
            "List of unpaid bills",
            "Quit"
        });
        BillList = new ListBill();
        BillList.loadData("electricityBill.txt");
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                DisplayBills();
                break;
            case 2:
                addNewBill();
                break;
            case 3:
                deleteBills();
                break;
            case 4:
                sortBills();
                break;
            case 5:
                searchBills();
                break;
            case 6:
                largestAmountBills();
                break;
            case 7:
                unpaidBills();
                break;
            case 8:
                System.out.println("Exiting the program.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }

    // add new bill,    public Bill(int Id, String customerName, Double amount, String dueDate, Boolean isPaid) throws ParseException {
    private void addNewBill() {
        try {
            int id;
            while (true) {
                id = (Utils.getIntValue("Enter bill ID:"));
                if (!BillList.contains(id)) {
                    break;
                } else {
                    System.out.println("ID already exists.");
                }
            }
            String name = Utils.getValue("Enter customer name:");
            Double amount = Utils.getDoubleValue("Enter amount:");
            String dueDate = Utils.formatDate(Utils.getDateValue("Enter due date  (dd/MM/yyyy):"));
            Boolean isPaid = Boolean.valueOf(Utils.getValue("Customer is paid(True) or not(False) :"));

            boolean added = BillList.addBills(id, name, amount, dueDate, isPaid);
            if (added) {
                System.out.println("Patient added successfully.");
            } else {
                System.out.println("Failed to add bill.");
            }
        } catch (Exception e) {
            System.out.println("Error adding bill: " + e.getMessage());
        }
    }

    //Method for deleting an existing bill by ID, Customer name, or Due date(1.5 mark) 
    private void deleteBills() {
        String[] deleteOptions = {"Delete by ID", "Delete by Name", "Delete by due date",};
        Menu deleteMenu = new Menu("Delete bills", deleteOptions) {

            @Override
            public void execute(int option) {

                Predicate<Bill> predicate;
                boolean deleted = false;
                switch (option) {
                    case 1:
                        int id = (Utils.getIntValue("Enter bill ID:"));
                        predicate = bill -> bill.getId() == id;
                        deleted = BillList.deleteBills(predicate);
                        break;
                    case 2:
                        String name = Utils.getValue("Enter customer name:");
                        predicate = bill -> bill.getCustomerName().equals(name);
                        deleted = BillList.deleteBills(predicate);
                        break;
                    case 3:
                        String dueDate = Utils.formatDate(Utils.getDateValue("Enter due date  (dd/MM/yyyy):"));
                        predicate = bill -> bill.getDueDate().equals(dueDate);
                        deleted = BillList.deleteBills(predicate);
                        break;

                    default:
                        System.out.println("Invalid option.");
                        return;
                }
                if (deleted) {
                    System.out.println("Bicycle deleted successfully.");
                } else {
                    System.out.println("Bicycle not found.");
                }

            }
        };

        deleteMenu.run();
    }

    private void sortBills() {
        String[] sortOptions = {"Sort by ID", "Sort by Name", "Sort by due date",};
        Menu sortMenu = new Menu("Sort bills", sortOptions) {

            @Override
            public void execute(int option) {

                switch (option) {
                    case 1:
                        BillList.sortBills((b1, b2) -> Integer.compare(b1.getId(), b2.getId()));
                        DisplayBills();
                        break;
                    case 2:
                        BillList.sortBills((b1, b2) -> b1.getCustomerName().compareTo(b2.getCustomerName()));
                        DisplayBills();
                        break;
                    case 3:
                        BillList.sortBills((b1, b2) -> b1.getDueDate().compareTo(b2.getCustomerName()));
                        DisplayBills();
                        break;
                    default:
                        System.out.println("Invalid option.");
                        return;
                }

            }
        };
        sortMenu.run();

    }
    // search bill

    private void searchBills() {
        String[] searchOptions = {"Search by ID", "Search by Name", "Search by due date",};
        Menu searchMenu;
        searchMenu = new Menu("Search bills", searchOptions) {
            @Override
            public void execute(int option) {
                Predicate<Bill> predicate;
                switch (option) {
                    case 1:
                        int id = (Utils.getIntValue("Enter bill ID:"));
                        searchResults(predicate = bill -> bill.getId() == id);
                        break;
                    case 2:
                        String name = Utils.getValue("Enter customer name:");
                        searchResults(bill -> bill.getCustomerName().equals(name));
                        break;
                    case 3:
                        String dueDate = Utils.formatDate(Utils.getDateValue("Enter due date  (dd/MM/yyyy):"));
                        searchResults(bill -> bill.getDueDate().equals(dueDate));
                        break;
                    default:
                        System.out.println("Invalid option.");
                        return;
                }

            }
        };
        searchMenu.run();

    }

    private void searchResults(Predicate<Bill> predicate) {
        ArrayList<Bill> results = BillList.searchBills(predicate);
        if (!results.isEmpty()) {
            System.out.println("Search list:");
            for (Bill b : results) {
                System.out.println(b);
            }
        } else {
            System.out.println("No Found!");
        }
    }

    // + Method for finding the largest amount bill in the list 
    private void largestAmountBills() {
        Predicate<Bill> predicate;
        double largestAmount = BillList.largestAmount();
        searchResults(predicate = bill -> bill.getAmount() == largestAmount);

    }

    // Method for listing all unpaid bills 
    private void unpaidBills() {
        Predicate<Bill> predicate;
        searchResults(predicate = bill -> !bill.getIsPaid());

    }

    public static void main(String[] args) throws ParseException {
        BillManagement management = new BillManagement();
        management.run();
    }
}
