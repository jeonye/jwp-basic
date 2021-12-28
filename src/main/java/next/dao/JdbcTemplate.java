package next.dao;

import core.exception.DataAccessException;
import core.jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    public void update(String sql, Object... parameters) throws DataAccessException {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);) {
            for (int i=0; i<parameters.length; i++) {
                pstmt.setObject(i+1, parameters[i]);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @SuppressWarnings("rawtypes")
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) throws DataAccessException {
        ResultSet rs = null;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ) {
            for (int i=0; i<parameters.length; i++) {
                pstmt.setObject(i+1, parameters[i]);
            }

            rs = pstmt.executeQuery();

            List<T> resultList = new ArrayList<T>();
            while(rs.next()) {
                resultList.add(rowMapper.mapRow(rs));
            }
            return resultList;

        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new DataAccessException(e);
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... parameters) throws DataAccessException {
        List<T> result = query(sql, rowMapper, parameters);

        if(result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }

}
