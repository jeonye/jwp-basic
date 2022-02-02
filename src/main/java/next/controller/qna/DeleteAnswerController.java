package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.constants.QuestionConstants;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.Result;
import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class DeleteAnswerController extends AbstractController {
    private AnswerDao answerDao = new AnswerDao();
    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long answerId = Long.parseLong(request.getParameter("answerId"));

        ModelAndView mav = jsonView();
        try {
            answerDao.delete(answerId);

            // 답변 수 증가
            Question question = questionDao.updateOnCountOfAnswer(Long.parseLong(request.getParameter("questionId")), QuestionConstants.DELETE_ANSWER);

            mav.addObject("result", Result.ok()).addObject("question", question);
        } catch (DataAccessException e) {
            mav.addObject("result", Result.fail(e.getMessage()));
        }
        return mav;
    }
}
