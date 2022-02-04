package next.controller.qna;

import core.mvc.AbstractDeleteController;
import core.mvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestionController extends AbstractDeleteController{
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        deleteQuestion(request);
        return jspView("redirect:/");
    }

}
