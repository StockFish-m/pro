
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Magazine {
    private String title;
    private String publisher;
    private Date publishDate;
    private int issueNumber;
    private int pages;

    public Magazine(String title, String publisher, String publishDate, int issueNumber, int pages) throws ParseException {
        this.title = title;
        this.publisher = publisher;
        this.publishDate = setPublishDate(publishDate);
        this.issueNumber = issueNumber;
        this.pages = pages;
    }

    public Magazine() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(publishDate);
        }

    public Date setPublishDate(String publishDate) throws ParseException{
         SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
       return sdf.parse(publishDate);
    
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Magazine{" + "title=" + title + ", publisher=" + publisher + ", publishDate=" + getPublishDate() + ", issueNumber=" + issueNumber + ", pages=" + pages + '}';
    }
    
}
