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
        // TODO 구현 필요함.
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(createQueryForSelectAll());
            rs = pstmt.executeQuery();
            List<User> userList = (List<User>) mapRowForSelectAll(rs);

            return userList;

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
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
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(createQueryForSelectOne());
            setValuesForSelectOne(userId, pstmt);
            rs = pstmt.executeQuery();

            User user = (User) mapRowForSelectOne(rs);

            return user;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
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
