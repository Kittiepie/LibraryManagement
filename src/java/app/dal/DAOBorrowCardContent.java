/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dal;

import app.entity.BorrowCardContent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OwO
 */
public class DAOBorrowCardContent extends DBContext {

    public void addBorrowCardContent(BorrowCardContent content) {
        String sql = "INSERT INTO BorrowCardContent (borrowcard_id, book_id, borrow_date, return_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, content.getBorrowCardId());
            ps.setInt(2, content.getBookId());
            ps.setDate(3, new Date(content.getBorrowDate().getTime()));
            ps.setDate(4, new Date(content.getReturnDate().getTime()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBorrowCardContent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<BorrowCardContent> getBorrowCardContentsByBorrowCardId(int borrowCardId) {
        List<BorrowCardContent> contents = new ArrayList<>();
        String sql = "SELECT * FROM BorrowCardContent WHERE borrowcard_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, borrowCardId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BorrowCardContent content = new BorrowCardContent();
                content.setBorrowCardId(rs.getInt("borrowcard_id"));
                content.setBookId(rs.getInt("book_id"));
                content.setBorrowDate(rs.getDate("borrow_date"));
                content.setReturnDate(rs.getDate("return_date"));
                contents.add(content);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBorrowCardContent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contents;
    }

    public int getNoOfRecordsByBorrowCardId(int borrowCardId) {
        int noOfRecords = 0;
        String sql = "SELECT COUNT(*) FROM BorrowCardContent WHERE borrowcard_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, borrowCardId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                noOfRecords = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBorrowCardContent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return noOfRecords;
    }

    public List<BorrowCardContent> getBorrowCardContentByBorrowCardId(int borrowCardId) {
        List<BorrowCardContent> borrowCardContents = new ArrayList<>();
        String sql = "SELECT * FROM BorrowCardContent WHERE borrowcard_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, borrowCardId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BorrowCardContent content = new BorrowCardContent();
                content.setBorrowCardId(rs.getInt("borrowcard_id"));
                content.setBookId(rs.getInt("book_id"));
                content.setBorrowDate(rs.getDate("borrow_date"));
                content.setReturnDate(rs.getDate("return_date"));
                borrowCardContents.add(content);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return borrowCardContents;
    }

    public String getBookTitleByBookId(int bookId) {
        String sql = "SELECT book_title FROM Book WHERE book_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("book_title");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBorrowCardContent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void addOrUpdateBorrowCardContent(BorrowCardContent content) {
        String selectSql = "SELECT * FROM BorrowCardContent WHERE borrowcard_id = ? AND book_id = ?";
        String updateSql = "UPDATE BorrowCardContent SET borrow_date = ?, return_date = ? WHERE borrowcard_id = ? AND book_id = ?";
        String insertSql = "INSERT INTO BorrowCardContent (borrowcard_id, book_id, borrow_date, return_date) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement selectPs = connection.prepareStatement(selectSql)) {
            selectPs.setInt(1, content.getBorrowCardId());
            selectPs.setInt(2, content.getBookId());
            ResultSet rs = selectPs.executeQuery();
            
            if (rs.next()) {
                try (PreparedStatement updatePs = connection.prepareStatement(updateSql)) {
                    updatePs.setDate(1, new Date(content.getBorrowDate().getTime()));
                    updatePs.setDate(2, new Date(content.getReturnDate().getTime()));
                    updatePs.setInt(3, content.getBorrowCardId());
                    updatePs.setInt(4, content.getBookId());
                    updatePs.executeUpdate();
                }
            } else {
                try (PreparedStatement insertPs = connection.prepareStatement(insertSql)) {
                    insertPs.setInt(1, content.getBorrowCardId());
                    insertPs.setInt(2, content.getBookId());
                    insertPs.setDate(3, new Date(content.getBorrowDate().getTime()));
                    insertPs.setDate(4, new Date(content.getReturnDate().getTime()));
                    insertPs.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBorrowCardContent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteBorrowCard(int borrowCardId) {
        String sql = "DELETE FROM BorrowCardContent WHERE borrowcard_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, borrowCardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
