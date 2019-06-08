package ro.utcn.project.repository;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.project.entity.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Comment(resultSet.getInt("id"),
                resultSet.getString("text"),
                resultSet.getString("username"),
                resultSet.getString("date"),
                resultSet.getInt("review_id"));
    }
}
