package com.jv.bookshelf.dao;

import com.jv.bookshelf.connection.PgConnection;
import com.jv.bookshelf.model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaov
 */
public class BookDAO {

    public void save(Book book) throws DAOException {
        String sql = "insert into books (title, author, pages, release_date, value, description) "
                + "values (?, ?, ?, ?, ?, ?) returning id";

        try (Connection conn = PgConnection.getConexao();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getPages());
            pstmt.setObject(4, book.getReleaseDate(), Types.DATE);
            pstmt.setBigDecimal(5, book.getValue());
            pstmt.setString(6, book.getDescription());

            pstmt.execute();

            try (ResultSet rs = pstmt.getResultSet();) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    book.setId(id);
                }
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public List<Book> findAll() throws DAOException {
        String sql = "select * from books order by title";

        List<Book> books = new ArrayList<>();

        try (Connection conn = PgConnection.getConexao();
                Statement stmt = conn.createStatement();) {

            try (ResultSet rs = stmt.executeQuery(sql)) {
                Book book = null;

                while (rs.next()) {
                    book = buildBook(rs);

                    books.add(book);
                }
            }

            return books;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    private Book buildBook(ResultSet rs) throws SQLException {
        Book book = new Book();

        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setPages(rs.getInt("pages"));
        book.setReleaseDate(rs.getDate("release_date").toLocalDate());
        book.setValue(rs.getBigDecimal("value"));
        book.setDescription(rs.getString("description"));

        return book;
    }

}
