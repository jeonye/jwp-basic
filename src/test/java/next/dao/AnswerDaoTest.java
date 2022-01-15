package next.dao;

import next.model.Answer;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;

import static org.junit.Assert.assertEquals;

public class AnswerDaoTest {
    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void addAnswer() throws Exception {
        long questionId = 1L;
        Answer expected = new Answer("javajigi", "answer contents", questionId);
        AnswerDao dut = new AnswerDao();
        Answer answer = dut.insert(expected);
        System.out.println("Answer : " + answer);
    }

    @Test
    public void deleteAnswer() throws Exception {
        long questionId = 1L;
        Answer expected = new Answer("javajigi", "answer contents", questionId);
        AnswerDao dut = new AnswerDao();
        Answer answer = dut.insert(expected);

        dut.delete(answer.getAnswerId());
        assertEquals(null, dut.findById(answer.getAnswerId()));
    }
}
