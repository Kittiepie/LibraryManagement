/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.entity;

import java.util.Date;
import java.util.List;

/**
 *
 * @author OwO
 */
public class BorrowCard {
    private int borrowCardId;
    private int userId;
    private Date createDate;
    private List<BorrowCardContent> borrowCardContents;

    public BorrowCard() {
    }

    public BorrowCard(int borrowCardId, int userId) {
        this.borrowCardId = borrowCardId;
        this.userId = userId;
    }

    public BorrowCard(int borrowCardId, int userId, Date createDate) {
        this.borrowCardId = borrowCardId;
        this.userId = userId;
        this.createDate = createDate;
    }

    public BorrowCard(int borrowCardId, int userId, Date createDate, List<BorrowCardContent> borrowCardContents) {
        this.borrowCardId = borrowCardId;
        this.userId = userId;
        this.createDate = createDate;
        this.borrowCardContents = borrowCardContents;
    }

    
    
    

    // Getters and Setters
    public int getBorrowCardId() {
        return borrowCardId;
    }

    public void setBorrowCardId(int borrowCardId) {
        this.borrowCardId = borrowCardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<BorrowCardContent> getBorrowCardContents() {
        return borrowCardContents;
    }

    public void setBorrowCardContents(List<BorrowCardContent> borrowCardContents) {
        this.borrowCardContents = borrowCardContents;
    }

    
    

}
