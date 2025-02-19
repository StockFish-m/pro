/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public final class Bill {
    private int Id;
    private String customerName;
    private Double amount;
    private Date dueDate;
    private boolean isPaid;
    

    public Bill(int Id, String customerName, Double amount, String dueDate, boolean isPaid) throws ParseException {
        this.Id = Id;
        this.customerName = customerName;
        this.amount = amount;
        this.dueDate = setDueDate(dueDate);
       this.isPaid = isPaid;
               }
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDueDate() {
         SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dueDate);
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Date setDueDate(String dueDate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
       return sdf.parse(dueDate);
    }

    @Override

   
    public String toString() {
        return "Bill{" + "Id=" + Id + ", customerName=" + customerName + ", amount=" + amount + ", dueDate=" + getDueDate() + ", isPaid=" + isPaid + '}';
    }
   
    
    
    
    
}
