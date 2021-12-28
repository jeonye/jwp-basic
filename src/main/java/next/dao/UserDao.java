package next.dao;

import java.sql.*;
import java.util.List;

import next.model.User;

public class UserDao {
    public void insert(User user) {
        JdbcTemplate template = new JdbcTemplate();
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        template.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) {
        JdbcTemplate template = new JdbcTemplate();
        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";
        template.update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() {
        JdbcTemplate template = new JdbcTemplate();
        String sql = "SELECT userId, password, name, email FROM USERS ORDER BY userId";
        return template.query(sql, (ResultSet rs) -> {
            return new User(
                    rs.getString("userId"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email")
            );
        });
    }

    public User findByUserId(String userId) {
        JdbcTemplate template = new JdbcTemplate();
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        return template.queryForObject(sql, (ResultSet rs) -> {
            return new User(
                    rs.getString("userId"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email")
            );
        }, userId);
    }
}
