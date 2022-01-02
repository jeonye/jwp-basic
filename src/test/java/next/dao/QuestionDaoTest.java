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

        QuestionDao qnaDao = new QuestionDao();
        qnaDao.insert(expected);
        Question actual = qnaDao.findByQuestionId(expected.getQuestionId());
        assertTrue(expected.equals(actual));

        // Update, Select
        expected.update(new Question(9000, "writer", "title", "contents", null, 2));
        qnaDao.update(expected);
        actual = qnaDao.findByQuestionId(expected.getQuestionId());
        assertTrue(expected.equals(actual));
    }

    @Test
    public void findMaxQuestionId() {
        Long questionId = 9100L;
        Question expected = new Question(questionId, "writer", "title", "contents", null, 0);
        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(expected);
        Long maxQuestionId = questionDao.findMaxQuestionId();
        assertEquals(questionId, maxQuestionId);
    }

    @Test
    public void findAll() {
        QuestionDao questionDao = new QuestionDao();
        List<Question> questionList = questionDao.findAll();
        assertEquals(8, questionList.size());
    }

}
