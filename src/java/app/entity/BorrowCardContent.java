/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.entity;

import java.util.Date;

/**
 *
 * @author OwO
 */
public class BorrowCardContent {
    private int borrowCardId;
    private int bookId;
    private Date borrowDate;
    private Date returnDate;

    
    public BorrowCardContent(int borrowCardId, int bookId, Date borrowDate, Date returnDate) {
        this.borrowCardId = borrowCardId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BorrowCardContent() {
    }
    
    

    public int getBorrowCardId() {
        return borrowCardId;
    }
    
    public void setBorrowCardId(int borrowCardId) {
        this.borrowCardId = borrowCardId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
