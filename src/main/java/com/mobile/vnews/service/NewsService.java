package com.mobile.vnews.service;


import com.mobile.vnews.mapper.NewsMapper;
import com.mobile.vnews.module.BasicResponse;
import com.mobile.vnews.module.bean.News;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    NewsMapper newsMapper;

    private static  Logger log = LogManager.getLogger(NewsService.class);

    /**
     *根据种类获得新闻
     * @param news
     * @param start
     * @param count
     * @return
     */
    public BasicResponse<List<News>> getNews(String category, int start, int count) {
        BasicResponse<List<News>> response = new BasicResponse<>();
        int code = 200;
        String message = "get category news success";
        try{
            ArrayList<News> news;
            if (category == null) {
                news = (ArrayList<News>) newsMapper.getNews(start, count);
            } else {
                news = (ArrayList<News>) newsMapper.getNewsByType(category, start, count);
            }
            if (news.isEmpty()) {
                message = "no more";
            }
            response.setContent(news);
        } catch (Exception e){
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    /**
     *返回最热新闻
     * @param count
     * @return
     */
    public BasicResponse<List<News>> getHotNews(int count) {
        BasicResponse<List<News>>response = new BasicResponse<>();
        int code = 200;
        String message="get hot news success";
        try{
            List<News> news = newsMapper.getHotNews(count);
            response.setContent(news);
        }catch (Exception e){
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return  response;
    }

    /**
     *根据id获取新闻
     * @param ID
     * @return
     */
    public BasicResponse<News> getNewsByID(int ID) {
        BasicResponse<News> response = new BasicResponse<>();
        int code = 200;
        String message = "success";
        try{
            News news = newsMapper.getNewsByID(ID);
            response.setContent(news);
        }catch (Exception e){
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return  response;
    }

    /**
     *根据用户id获取最喜爱新闻
     * @param UserID
     * @return
     */
    public BasicResponse<List<News>> getFavoriteNewsByUserID(String UserID) {
        BasicResponse<List<News>> response = new BasicResponse<>();
        int code = 200;
        String message = "get favorite news success";
        try{
            List<News> news = newsMapper.getFavoriteNewsByUserID(UserID);
            response.setContent(news);
        }catch (Exception e){
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return  response;
    }

    /**
     *添加最喜爱新闻
     * @param userID
     * @param newsID
     * @return
     */
    public BasicResponse<String> addFavoriteNews(String userID, int newsID) {
        BasicResponse<String> response = new BasicResponse<>();
        int code = 200;
        String message = "add favorite news success";
        try{
            newsMapper.addFavoriteNews(userID, newsID);
        }catch (Exception e){
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        response.setContent("");
        return response;
    }

    /**
     *检查某新闻是不是最喜爱新闻
     * @param userID
     * @param newsID
     * @return
     */
    public BasicResponse<String> checkFavoriteNews(String userID, int newsID) {
        BasicResponse<String> response=new BasicResponse<>();
        int code = 200;
        String message = "favorite news exists";
        String result = "false";
        try{
            int res = newsMapper.checkFavoriteNews(userID, newsID);
            if (res > 0) {
                result = "true";
            }
        }catch (Exception e){
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        response.setContent(result);
        return response;
    }
    //删除最喜爱新闻
    public  BasicResponse<String> deleteFavoriteNews(String userID, int newsID) {
        BasicResponse<String> response=new BasicResponse<>();
        int code = 200;
        String message = "delete favorite news success";
        try{
            newsMapper.deleteFavoriteNews(userID, newsID);
        }catch (Exception e){
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        response.setContent("");
        return  response;
    }

    /**
     *添加最喜爱新闻
     * @param userID
     * @param newsID
     * @return
     */
    public BasicResponse<String> addViewNews(String userID, int newsID) {
        BasicResponse<String> response = new BasicResponse<>();
        int code=200;
        String message="the news has been viewed";
        try{
            newsMapper.addViewNews(userID, newsID);
        }catch ( Exception e){
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    /**
     *通过用户id得到浏览新闻消息
     * @param userID
     * @return
     */
    public BasicResponse<List<News>> getViewNewsByUserID(String userID) {
        BasicResponse<List<News>> response = new BasicResponse<>();
        int code = 200;
        String message = "the news has been viewed";
        try{
            List<News> news = newsMapper.getViewNewsByUserID(userID);
            response.setContent(news);
        }catch ( Exception e){
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
