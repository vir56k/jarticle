package com.zhangyfvir.jarticle.articleservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangyfvir.jarticle.articleservice.contract.Article;
import com.zhangyfvir.jarticle.articleservice.contract.ArticleProtectedInterface;
import com.zhangyfvir.jarticle.articleservice.services.ArticleProtectedService;
import com.zhangyfvir.jarticle.common.entity.Result;
import com.zhangyfvir.jarticle.utils.Const;
import com.zhangyfvir.jarticle.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/protected")
public class ArticleProtectedController implements ArticleProtectedInterface {

    private static final String TAG = ArticleProtectedController.class.getSimpleName();

    @Autowired
    ArticleProtectedService articleProtectedService;


    @GetMapping("/articles/{id}")
    @Override
    public Result<Article> getArticle(@PathVariable String id) {
        Article article = articleProtectedService.GetArticle(id);
        return Result.success(article);
    }

    @GetMapping("/articleNameList")
    @Override
    public Result<Article[]> getNameList() {
        Article[] strings = articleProtectedService.GetNameList();
        return Result.success(strings);
    }

    @PostMapping("/articles")
    @Override
    public Result save(@RequestParam String id, @RequestParam String title, @RequestParam String body) {
        ObjectMapper mapper = new ObjectMapper();
        try {
//            String p = mapper.writeValueAsString(article);
//            LogUtil.d(TAG, "arg:%s", p);
            articleProtectedService.save(new Article(id, title, body));
            return Result.success();
        } catch (Exception e) {
            String msg1 = e.getMessage();
            LogUtil.e(TAG, "***" + msg1);
            e.printStackTrace();
            LogUtil.e(TAG, e);
            return Result.error(500, e);
        }
    }
}
