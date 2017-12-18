package com.mobile.vnews.controller;

import com.alibaba.fastjson.JSON;
import com.mobile.vnews.mapper.UserMapper;
import com.mobile.vnews.module.BasicResponse;
import com.mobile.vnews.module.bean.*;
import com.mobile.vnews.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vnews/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    /**
     * 返回所有种类的新闻
     * @param start
     * @param count
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public BasicResponse<List<News>> allCategoryNews(@RequestParam("start")int start,
                                                     @RequestParam("count")int count) {
        return newsService.getNews("", start, count);
    }

    /**
     * 返回特定类型种类的新闻
     * @param category
     * @param start
     * @param count
     * @return
     */
    @RequestMapping(value = "{category}",method = RequestMethod.GET)
    public BasicResponse<List<News>> CategoryNews(@PathVariable("category") String category,
                                                  @RequestParam("start")int start,
                                                  @RequestParam("count")int count) {
        return newsService.getNews(category, start, count);
    }

    /**
     * 返回热门新闻
     * @param count
     * @return
     */
    @RequestMapping(value = "/hots",method = RequestMethod.GET)
    public BasicResponse<List<News>> hotNews(@RequestParam("count") int count) {
        return newsService.getHotNews(count);
    }

    /**
     * 返回新闻细节
     * @param ID
     * @return
     */
    @RequestMapping(value="/detail/{news_id}",method = RequestMethod.GET)
    public BasicResponse<News> detail(@PathVariable("news_id") int ID){
        return newsService.getNewsByID(ID);
    }

    /**
     * 根据用户id得到用户喜爱的新闻
     * @param user_id
     * @return
     */
    @RequestMapping(value="/{user_id}/likes",method = RequestMethod.GET)
    public BasicResponse<List<News>> favoriteNews(@PathVariable("user_id") String user_id) {
        return newsService.getFavoriteNewsByUserID(user_id);
    }

    /**
     * 添加用户喜爱的新闻
     * @param user_id
     * @param news_id
     * @return
     */
    @RequestMapping(value="/like",method = RequestMethod.POST)
    public BasicResponse<String> addFavoriteNews(@RequestParam("user_id") String user_id,
                                                 @RequestParam("news_id") int news_id) {
        return newsService.addFavoriteNews(user_id, news_id);
    }

    /**
     * 查询某个新闻是否是用户喜爱的新闻
     * @param user_id
     * @param news_id
     * @return
     */
    @RequestMapping(value="/{user_id}/like/{news_id}",method = RequestMethod.GET)
    public BasicResponse<String> checkFavoriteNews(@PathVariable("user_id") String user_id,
                                                   @PathVariable("news_id") int news_id) {
        return newsService.checkFavoriteNews(user_id,news_id);
    }

    /**
     * 删除用户喜爱的新闻
     * @param user_id
     * @param news_id
     * @return
     */
    @RequestMapping(value="/{user_id}/like/{news_id}",method = RequestMethod.DELETE)
    public BasicResponse<String> deleteFavoriteNews(@PathVariable("user_id") String user_id,
                                                    @PathVariable("news_id") int news_id) {
        return newsService.deleteFavoriteNews(user_id, news_id);
    }

    /**
     * 添加浏览过的新闻
     * @param news_id
     * @param user_id
     * @return
     */
    @RequestMapping(value = "/view",method = RequestMethod.POST)
    public BasicResponse<String> addViewedNews(@RequestParam("user_id") String user_id,
                                               @RequestParam("news_id") int news_id) {
        return  newsService.addViewNews(user_id, news_id);
    }

    /**
     * 得到浏览过的新闻
     * @param user_id
     * @return
     */
    @RequestMapping(value="/{user_id}/views",method = RequestMethod.GET)
    public BasicResponse<List<News>> getViewNews(@PathVariable("user_id")String user_id) {
        return newsService.getViewNewsByUserID(user_id);
    }
}