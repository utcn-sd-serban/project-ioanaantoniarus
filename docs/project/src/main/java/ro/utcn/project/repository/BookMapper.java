package ro.utcn.project.repository;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.project.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Book(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("author"),
                resultSet.getString("isbn"));
    }
}
