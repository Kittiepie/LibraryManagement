/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dal;

/**
 *
 * @author OwO
 */
import app.entity.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOBook extends DBContext {

    public List<Book> getFeaturedBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Book WHERE is_featured = 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("book_id"),
                        rs.getString("book_title"),
                        rs.getInt("category_id"),
                        rs.getBoolean("book_status"),
                        rs.getBoolean("is_featured"),
                        rs.getString("book_cover"),
                        rs.getString("brief_info"),
                        rs.getString("description")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Book";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("book_id"),
                        rs.getString("book_title"),
                        rs.getInt("category_id"),
                        rs.getBoolean("book_status"),
                        rs.getBoolean("is_featured"),
                        rs.getString("book_cover"),
                        rs.getString("brief_info"),
                        rs.getString("description")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> searchBooksByTitle(String title) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM Book WHERE book_title LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + title + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getInt("book_id"),
                            rs.getString("book_title"),
                            rs.getInt("category_id"),
                            rs.getBoolean("book_status"),
                            rs.getBoolean("is_featured"),
                            rs.getString("book_cover"),
                            rs.getString("brief_info"),
                            rs.getString("description")
                    );
                    list.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Book> filterBooksByCategory(int categoryId) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM Book WHERE category_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getInt("book_id"),
                            rs.getString("book_title"),
                            rs.getInt("category_id"),
                            rs.getBoolean("book_status"),
                            rs.getBoolean("is_featured"),
                            rs.getString("book_cover"),
                            rs.getString("brief_info"),
                            rs.getString("description")
                    );
                    list.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Book getBookById(int bookId) {
        String sql = "SELECT b.*, c.category_name FROM [Book] b JOIN [BookCategory] c ON b.category_id = c.category_id WHERE b.book_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("book_title"),
                        rs.getInt("category_id"),
                        rs.getBoolean("book_status"),
                        rs.getBoolean("is_featured"),
                        rs.getString("book_cover"),
                        rs.getString("brief_info"),
                        rs.getString("description")
                );
                book.setCategoryName(rs.getString("category_name"));
                return book;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBook(Book book) {
        String sql = "UPDATE Book SET book_title = ?, category_id = ?, book_status = ?, is_featured = ?, book_cover = ?, brief_info = ?, description = ? WHERE book_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, book.getBookTitle());
            ps.setInt(2, book.getCategoryId());
            ps.setBoolean(3, book.isBookStatus());
            ps.setBoolean(4, book.isFeatured());
            ps.setString(5, book.getBookCover());
            ps.setString(6, book.getBriefInfo());
            ps.setString(7, book.getDescription());
            ps.setInt(8, book.getBookId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTotalBooksCount() {
        String sql = "SELECT COUNT(*) FROM Book";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Book> getBooksByPage(int pageIndex, int pageSize) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Book ORDER BY book_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, (pageIndex - 1) * pageSize);
            stmt.setInt(2, pageSize);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("book_id"),
                        rs.getString("book_title"),
                        rs.getInt("category_id"),
                        rs.getBoolean("book_status"),
                        rs.getBoolean("is_featured"),
                        rs.getString("book_cover"),
                        rs.getString("brief_info"),
                        rs.getString("description")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO Book (book_title, category_id, book_status, is_featured, book_cover, brief_info, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, book.getBookTitle());
            ps.setInt(2, book.getCategoryId());
            ps.setBoolean(3, book.isBookStatus());
            ps.setBoolean(4, book.isFeatured());
            ps.setString(5, book.getBookCover());
            ps.setString(6, book.getBriefInfo());
            ps.setString(7, book.getDescription());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}