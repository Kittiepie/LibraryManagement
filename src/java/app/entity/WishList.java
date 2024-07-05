/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.entity;

/**
 *
 * @author OwO
 */
public class WishList {
    private int wish_id;
    private int user_id;
    private int book_id;
    private String book_title;
    private String book_cover;

    public WishList(int wish_id, int user_id, int book_id) {
        this.wish_id = wish_id;
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public WishList(int wish_id, int user_id, int book_id, String book_cover) {
        this.wish_id = wish_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.book_cover = book_cover;
    }

    public WishList(int user_id, int book_id) {
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public WishList() {
    }

    public int getWish_id() {
        return wish_id;
    }

    public void setWish_id(int wish_id) {
        this.wish_id = wish_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_cover() {
        return book_cover;
    }

    public void setBook_cover(String book_cover) {
        this.book_cover = book_cover;
    }
    
    
}
