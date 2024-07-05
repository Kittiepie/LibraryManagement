/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dal;

import app.entity.Book;
import app.entity.BorrowCard;
import app.entity.BorrowCardContent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OwO
 */
public class DAOBorrowCard extends DBContext {

    // Method to add a new borrow card
    public void addBorrowCard(BorrowCard borrowCard) {
        String sql = "INSERT INTO BorrowCard (user_id, create_date) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, borrowCard.getUserId());
            Date sqlCreateDate = new Date(borrowCard.getCreateDate().getTime());
            ps.setDate(2, sqlCreateDate);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBorrowCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to create a borrow card for a user
    public boolean createBorrowCard(int userId) {
        String sql = "INSERT INTO BorrowCard (user_id, borrow_date) VALUES (?, CURDATE())";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<BorrowCard> getBorrowCardsByUserId(int userId, int offset, int noOfRecords) {
        List<BorrowCard> borrowCards = new ArrayList<>();
        String sql = "SELECT * FROM BorrowCard WHERE user_id = ? ORDER BY borrowcard_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, offset);
            ps.setInt(3, noOfRecords);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BorrowCard borrowCard = new BorrowCard();
                borrowCard.setBorrowCardId(rs.getInt("borrowcard_id"));
                borrowCard.setUserId(rs.getInt("user_id"));
                borrowCard.setCreateDate(rs.getDate("create_date"));
                borrowCards.add(borrowCard);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBorrowCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return borrowCards;
    }

    public int getNoOfRecordsByUserId(int userId) {
        int noOfRecords = 0;
        String sql = "SELECT COUNT(*) FROM BorrowCard WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                noOfRecords = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBorrowCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return noOfRecords;
    }

    private List<BorrowCardContent> getBorrowCardContents(int borrowCardId) {
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
            Logger.getLogger(DAOBorrowCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contents;
    }

    public BorrowCard getBorrowCardById(int borrowCardId) {
        BorrowCard borrowCard = null;
        String sql = "SELECT * FROM BorrowCard WHERE borrowcard_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, borrowCardId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                borrowCard = new BorrowCard();
                borrowCard.setBorrowCardId(rs.getInt("borrowcard_id"));
                borrowCard.setUserId(rs.getInt("user_id"));
                borrowCard.setCreateDate(rs.getDate("create_date"));

                // Fetch borrow card contents
                DAOBorrowCardContent daoBorrowCardContent = new DAOBorrowCardContent();
                List<BorrowCardContent> borrowCardContents = daoBorrowCardContent.getBorrowCardContentByBorrowCardId(borrowCardId);
                borrowCard.setBorrowCardContents(borrowCardContents);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return borrowCard;
    }

    public BorrowCard getActiveBorrowCardByUserIdFake(int userId) {
        String sql = "SELECT * FROM BorrowCard WHERE user_id = ? ORDER BY create_date DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BorrowCard borrowCard = new BorrowCard();
                borrowCard.setBorrowCardId(rs.getInt("borrowcard_id"));
                borrowCard.setUserId(rs.getInt("user_id"));
                borrowCard.setCreateDate(rs.getDate("create_date"));
                return borrowCard;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBorrowCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public BorrowCard getActiveBorrowCardByUserId(int userId) {
        List<BorrowCard> borrowCards = new ArrayList<>();
        String sql = "SELECT TOP 1 bc.borrowcard_id, bc.user_id, bc.create_date "
                + "FROM BorrowCard bc "
                + "JOIN [User] u ON bc.borrowcard_id = u.borrowcard_id "
                + "WHERE u.id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BorrowCard borrowCard = new BorrowCard();
                borrowCard.setBorrowCardId(rs.getInt("borrowcard_id"));
                borrowCard.setUserId(rs.getInt("user_id"));
                borrowCard.setCreateDate(rs.getDate("create_date"));
                return borrowCard;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBorrowCard.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
