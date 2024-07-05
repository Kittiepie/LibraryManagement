/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.entity;

/**
 *
 * @author OwO
 */
public class Book {
    private int bookId;
    private String bookTitle;
    private int categoryId;
    private boolean bookStatus;
    private boolean isFeatured;
    private String bookCover;
    private String briefInfo;
    private String description;
    private String categoryName;

    public Book(int bookId, String bookTitle, int categoryId, boolean bookStatus, boolean isFeatured, String bookCover, String briefInfo, String description, String categoryName) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.categoryId = categoryId;
        this.bookStatus = bookStatus;
        this.isFeatured = isFeatured;
        this.bookCover = bookCover;
        this.briefInfo = briefInfo;
        this.description = description;
        this.categoryName = categoryName;
    }
    
    

    public Book(int bookId, String bookTitle, int categoryId, boolean bookStatus, boolean isFeatured, String bookCover, String briefInfo, String description) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.categoryId = categoryId;
        this.bookStatus = bookStatus;
        this.isFeatured = isFeatured;
        this.bookCover = bookCover;
        this.briefInfo = briefInfo;
        this.description = description;
    }

    public Book(int bookId, String bookTitle, String bookCover) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookCover = bookCover;
    }

    // Getters and setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
}