package ro.utcn.project.repository;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.project.entity.Review;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Review(resultSet.getInt("id"),
                resultSet.getInt("id_book"),
                resultSet.getString("title"),
                resultSet.getString("username"),
                resultSet.getString("text"),
                resultSet.getString("date"));
    }
}
