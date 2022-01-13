package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerDao {

    private static final Logger log = LoggerFactory.getLogger(AnswerDao.class);

    public void insert(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        // answerId, writer, contents, createdDate, questionId
        String sql = "INSERT INTO ANSWERS VALUES(?, ?, ?, CURRENT_TIMESTAMP(), ?)";
        jdbcTemplate.update(sql, answer.getAnswerId(), answer.getWriter(), answer.getContents(), answer.getQuestionId());
        log.debug("[QnA] Success Registered Of Answer / QuestionId : {}, AnswerId : {}", answer.getQuestionId(), answer.getAnswerId());
    }

    public void update(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE ANSWERS SET writer = ?, contents = ?, createdDate = ? WHERE answerId = ?";
        jdbcTemplate.update(sql, answer.getWriter(), answer.getContents(), answer.getCreatedDate(), answer.getAnswerId());
    }

    public void delete(long answerId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "DELETE FROM ANSWERS WHERE answerId = ?";
        jdbcTemplate.update(sql, answerId);
    }

    public void updateForAnswerCount(long questionId, int countType) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String countStr = "+ 0";

        // countType 0 : 1 증가, 1 : 1 감소
        if(countType == 0) {
            countStr = "+ 1";
        } else if (countType == 1) {
            countStr = "- 1";
        }

        String sql = "UPDATE QUESTIONS SET countOfAnswer = countOfAnswer " + countStr +" WHERE questionId = ?";
        jdbcTemplate.update(sql, questionId);
    }

    public Answer findByAnswerId(long answerId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE answerId = ?";

        RowMapper<Answer> rowMapper = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(rs.getLong("answerId"), rs.getString("writer"),
                        rs.getString("contents"), rs.getString("createdDate"), rs.getLong("questionId"));
            }
        };

        Answer answer = jdbcTemplate.queryForObject(sql, rowMapper, answerId);
        return answer;
    }

    public Long findMaxAnswerId() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT MAX(answerId) as maxAnswerId FROM ANSWERS";
        RowMapper<Long> rm = new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs) throws SQLException {
                return rs.getLong("maxAnswerId");
            }
        };
        return jdbcTemplate.queryForObject(sql, rm);
    }
}
