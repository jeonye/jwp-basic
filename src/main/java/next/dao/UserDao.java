package next.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };

        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";

        template.update(sql);
    }

    public void update(User user) throws SQLException {
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            }
        };

        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";

        template.update(sql);
    }

    public List<User> findAll() throws SQLException {
        SelectAllJdbcTemplate template = new SelectAllJdbcTemplate();
        List<User> userList = template.findAll(this);

        return userList;
    }

    String createQueryForSelectAll() {
        return "SELECT userId, password, name, email FROM USERS ORDER BY userId";
    }

    Object mapRowForSelectAll(ResultSet rs) throws SQLException {
        List<User> userList = new ArrayList<User>();
        User user = null;

        if (rs.next()) {
            user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                    rs.getString("email"));
            userList.add(user);
        }

        return userList;
    }

    public User findByUserId(String userId) throws SQLException {
        SelectOneJdbcTemplate template = new SelectOneJdbcTemplate();
        User user = template.findByUserId(userId, this);

        return user;
    }

    String createQueryForSelectOne() {
        return "SELECT userId, password, name, email FROM USERS WHERE userid=?";
    }

    void setValuesForSelectOne(String userId, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, userId);
    }

    Object mapRowForSelectOne(ResultSet rs) throws SQLException {
        User user = null;

        if (rs.next()) {
            user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                    rs.getString("email"));
        }

        return user;
    }
}
