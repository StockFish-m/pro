/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author ADMIN
 */
public final class ListBill {

    private ArrayList<Bill> bills;

    public ListBill() {
        this.bills = new ArrayList<>();
//        loadData("electricityBill.txt");
    }

    public void printBills() {
        for (Bill bill : bills) {
            System.out.println(bill);
        }
    }

    public void loadData(String fName) {
        String cus = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fName))) {
            while ((cus = br.readLine()) != null) {
                String[] b = cus.split(",");

                if (b.length != 5) {
                    System.out.println("Invalid line format: " + cus);
                    continue;
                }

                //1,Nguyen Van An,520000,20/05/2024,True
                if (isInt(b[0]) && isDouble(b[2]) && isValid(b[3]) && isBoolean(b[4])) {
                    bills.add(new Bill(getInt(b[0]), b[1], getDouble(b[2]), b[3], getBoolean(b[4])));
                } else {
                    System.out.println("new error entry");
                    System.out.println(isInt(b[0]));
                    System.out.println(isDouble(b[2]));
                    System.out.println(isValid(b[3]));
                    System.out.println(isBoolean(b[4]));

                }
            }
            
        } catch (Exception ex) {
            System.out.println("cannot read file!");
        }

    }

    public boolean isValid(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public Date getDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            System.err.println("Invalid date format: " + e.getMessage());
            return null;
        }
    }

    public boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Double getDouble(String input) {
        return Double.parseDouble(input);
    }

    public boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getInt(String input) {
        return Integer.parseInt(input);
    }

    public boolean isBoolean(String input) {
        return input.equals("True") || input.equals("False");
    }

    public boolean getBoolean(String input) {
        return Boolean.parseBoolean(input);
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    // Method that can add a new bill (check this bill id does not exist in the list is required)
    //Id (int), customerName (string), amount (double), dueDate (Date).
    public boolean addBills(int Ide, String customerName, Double amount, String dueDate, boolean ispaid) {

        try {
            bills.add(new Bill(Ide, customerName, amount, dueDate, ispaid));
            return true;
        } catch (ParseException ex) {
            System.out.println("Error adding patient: " + ex.getMessage());
            return false;
        }
    }

    public boolean contains(int id) {
        for (Bill bill : bills) {
            if (id == bill.getId()) {
                return true;
            }
        }
        return false;

    }
    //+ Method that can delete a Bill by any criteria (by id, name, due date) 

    public boolean deleteBills(Predicate<Bill> predicate) {
        return bills.removeIf(predicate);

    }

    // Sort bill (by id, name , due date)
    public void sortBills(Comparator<Bill> comparator) {
        bills.sort(comparator);

    }

    // search bill (by id, name , due date)
    public ArrayList<Bill> searchBills(Predicate<Bill> predicate) {
        ArrayList<Bill> results = new ArrayList();
        for (Bill b : bills) {
            if (predicate.test(b)) {
                results.add(b);
            }
        }
        return results;
    }

    //Method that can get a list of unpaid bills 
    // + Method that can get the largest amount of bill 
    public double largestAmount() {
        double amount = 0;
        for (Bill b : bills) {
            if (amount < b.getAmount()) {
                amount = b.getAmount();
            }
        }
        return amount;
    }

}
