package next.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import core.jdbc.ConnectionManager;
import next.model.Question;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionDaoTest {
    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void crud() {
        // Insert, Select
        Question expected = new Question(9000, "writer", "title", "contents", null, 0);

        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(expected);
        Question actual = questionDao.findByQuestionId(expected.getQuestionId());
        assertTrue(expected.equals(actual));

        // Update, Select
        expected.update(new Question(9000, "writer", "title", "contents", LocalDateTime.now().toString(), 0));
        questionDao.update(expected);
        actual = questionDao.findByQuestionId(expected.getQuestionId());
        assertTrue(expected.equals(actual));

        // Delete
        questionDao.delete(expected.getQuestionId());
        actual = questionDao.findByQuestionId(expected.getQuestionId());
        assertEquals(null, actual);
    }

    @Test
    public void findMaxQuestionId() {
        long questionId = 9100L;
        Question expected = new Question(questionId, "writer", "title", "contents", null, 0);
        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(expected);
        long maxQuestionId = questionDao.findMaxQuestionId();
        assertEquals(questionId, maxQuestionId);
    }

    @Test
    public void findAll() {
        QuestionDao questionDao = new QuestionDao();
        List<Question> questionList = questionDao.findAll();
        assertEquals(8, questionList.size());
    }

}
