package ro.utcn.project.repository;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.project.entity.Rating;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingMapper implements RowMapper<Rating> {
    @Override
    public Rating mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Rating(resultSet.getInt("rating_sum"),
                resultSet.getInt("total_ratings"),
                resultSet.getFloat("final_rating"));

    }
}
