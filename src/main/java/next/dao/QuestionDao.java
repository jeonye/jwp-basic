package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Answer;
import next.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionDao {

    private static final Logger log = LoggerFactory.getLogger(QuestionDao.class);

    public void insert(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        // Column : questionId, writer, title, contents, createdDate, countOfAnswer
        String sql = "INSERT INTO QUESTIONS VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP(), 0)";
        jdbcTemplate.update(sql, question.getQuestionId(), question.getWriter(), question.getTitle(), question.getContents());
    }

    public void update(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "UPDATE QUESTIONS set writer = ?, title = ?, contents = ?, countOfAnswer = ?";
        jdbcTemplate.update(sql, question.getWriter(), question.getTitle(), question.getContents(), question.getCountOfAnswer());
    }

    public Question findByQuestionId(Long questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        // Question 데이터 조회
        String sql = "SELECT questionId, writer, title, contents, formatdatetime(createdDate, 'yyyy-MM-dd HH:mm') as createdDate, countOfAnswer from QUESTIONS where questionId = ?";

        RowMapper<Question> questionRowMapper = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getLong("questionId"), rs.getString("writer"),
                        rs.getString("title"), rs.getString("contents"),
                        rs.getString("createdDate"), rs.getInt("countOfAnswer"));
            }
        };

        Question question = jdbcTemplate.queryForObject(sql, questionRowMapper, questionId);

        log.debug("[QnA] Selected Question ID : {}", question.getQuestionId());

        // Question에 대한 Answer 조회
        sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE questionId = ?";

        RowMapper<Answer> answerRowMapper = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(rs.getLong("answerId"), rs.getString("writer"),
                        rs.getString("contents"), rs.getString("createdDate"),
                        rs.getLong("questionId"));
            }
        };

        List<Answer> answerList = jdbcTemplate.query(sql, answerRowMapper, questionId);

        log.debug("[QnA] Selected Answer Count : {}", answerList.size());

        question.setAnswerList(answerList);

        return question;
    }

    public List<Question> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT questionId, writer, title, contents, formatdatetime(createdDate, 'yyyy-MM-dd HH:mm') as createdDate, countOfAnswer from QUESTIONS ORDER BY createdDate DESC";

        RowMapper<Question> questionRowMapper = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getLong("questionId"), rs.getString("writer"),
                        rs.getString("title"), rs.getString("contents"),
                        rs.getString("createdDate"), rs.getInt("countOfAnswer"));
            }
        };

        return jdbcTemplate.query(sql, questionRowMapper);
    }

    public long findMaxQuestionId() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT MAX(questionId) as maxQuestionId FROM QUESTIONS";
        RowMapper<Long> rm = new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs) throws SQLException {
                return rs.getLong("maxQuestionId");
            }
        };
        return jdbcTemplate.queryForObject(sql, rm).longValue();
    }
}
