package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateQuestionController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        Question updateQuestion = new Question(Long.parseLong(request.getParameter("questionId")),
                request.getParameter("title"), request.getParameter("contents"));

        questionDao.update(updateQuestion);

        return jspView("/");
    }
}
