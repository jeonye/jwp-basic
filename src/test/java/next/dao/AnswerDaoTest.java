package next.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import core.jdbc.ConnectionManager;
import next.model.Answer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.time.LocalDateTime;

public class AnswerDaoTest {
    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void crud() {
        // Insert, Select
        Answer expected = new Answer(9000, "writer", "contents", null, 9000);

        AnswerDao answerDao = new AnswerDao();
        answerDao.insert(expected);
        Answer actual = answerDao.findByAnswerId(expected.getAnswerId());
        assertTrue(expected.equals(actual));

        // Update, Select
        expected.update(new Answer(9000, "writer", "contents", LocalDateTime.now().toString(), 9000));
        answerDao.update(expected);
        actual = answerDao.findByAnswerId(9000);
        assertTrue(expected.equals(actual));

        // Delete
        answerDao.delete(expected.getAnswerId());
        actual = answerDao.findByAnswerId(expected.getAnswerId());
        assertEquals(null, actual);
    }
}
