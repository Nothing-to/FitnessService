package com.code.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author 刘冠麟
 */
public class TrainServlet extends BaseMobileServlet {

    private static final long serialVersionUID = -4111383568403919681L;

    public String addNewTrainRecord(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String duration = request.getParameter("duration");
        if (trainDao.addNewTrainRecord(userId, duration)) {
            return "success";
        } else {
            return "error";
        }

    }


}
