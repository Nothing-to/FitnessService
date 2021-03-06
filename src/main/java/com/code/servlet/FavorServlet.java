package com.code.servlet;

import com.code.entity.NewsListItem;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @author 刘冠麟
 */
public class FavorServlet extends BaseMobileServlet {

    private static final long serialVersionUID = -1543111033584564605L;

    public String getFavorsList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        List<NewsListItem> list = favorsDao.getCommentsList(userId);
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    public String addNewFavor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String newsId = request.getParameter("newsId");
        if (favorsDao.isfavored(userId, newsId)) {
            return "您已收藏，无需再次操作";
        } else {
            if (favorsDao.addNewFavor(userId, newsId)) {
                return "收藏成功";
            } else {
                return "操作失败，请稍后再试";
            }
        }
    }

}
