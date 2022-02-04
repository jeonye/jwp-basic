package core.mvc;

import next.dao.QuestionDao;
import javax.servlet.http.HttpServletRequest;

public abstract class AbstractDeleteController extends AbstractController {
    protected void deleteQuestion(HttpServletRequest request) {
        long questionId = Long.parseLong(request.getParameter("questionId"));

        QuestionDao questionDao = new QuestionDao();
        questionDao.delete(questionId);
    }
}
