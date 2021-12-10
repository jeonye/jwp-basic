package next.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        SelectJdbcTemplate template = new SelectJdbcTemplate() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {

            }

            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                List<User> userList = new ArrayList<User>();
                User user = null;

                if (rs.next()) {
                    user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                            rs.getString("email"));
                    userList.add(user);
                }

                return userList;
            }
        };

        String sql = "SELECT userId, password, name, email FROM USERS ORDER BY userId";

        List<User> userList = (List<User>) template.query(sql);

        return userList;
    }

    public User findByUserId(String userId) throws SQLException {
        SelectJdbcTemplate template = new SelectJdbcTemplate() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }

            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                User user = null;

                if (rs.next()) {
                    user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                            rs.getString("email"));
                }

                return user;
            }
        };

        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

        User user = (User) template.queryForObject(sql);

        return user;
    }

}
