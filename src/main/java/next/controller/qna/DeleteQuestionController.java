package next.controller.qna;

import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.constants.QuestionConstants;
import next.dao.QuestionDao;
import next.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestionController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        long questionId = Long.parseLong(request.getParameter("questionId"));
        // 접속 기기 확인
        boolean isMobile = isMobile(request);

        try {
            QuestionDao questionDao = new QuestionDao();
            questionDao.delete(questionId);

            if (isMobile) {
                // 모바일을 통해 접근한 경우 응답 결과를 JSON으로 전송
                return jsonView().addObject("result", Result.ok());
            }
        } catch (DataAccessException e) {
            if(isMobile) {
                return jsonView().addObject("result", Result.fail(e.getMessage()));
            }
        }

        // 웹 브라우저를 통해 접근한 경우 목록 페이지로 이동
        return jspView("redirect:/");
    }

    private boolean isMobile(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent").toUpperCase();
        if(userAgent.indexOf(QuestionConstants.IS_MOBILE) > -1) {
            return true;
        } else {
            return false;
        }
    }
}
