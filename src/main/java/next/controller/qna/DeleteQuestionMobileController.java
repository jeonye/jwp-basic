package next.controller.qna;

import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.AbstractDeleteController;
import core.mvc.ModelAndView;
import next.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestionMobileController extends AbstractDeleteController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            deleteQuestion(request);
            return jsonView().addObject("result", Result.ok());
        } catch (DataAccessException e) {
            return jsonView().addObject("result", Result.fail(e.getMessage()));
        }
    }
}
