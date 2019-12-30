package pl.flaaaxxx.springhomeworkweek7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.flaaaxxx.springhomeworkweek7.dao.NewsDaoImpl;
import pl.flaaaxxx.springhomeworkweek7.model.Article;
import pl.flaaaxxx.springhomeworkweek7.model.News;
import pl.flaaaxxx.springhomeworkweek7.service.NewsService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsController {

    private NewsService newsService;
    private NewsDaoImpl newsDaoImpl;
    private static List<Article> newsList;

    @Autowired
    public NewsController(NewsService newsService, NewsDaoImpl newsDaoImpl) {
        this.newsService = newsService;
        this.newsDaoImpl = newsDaoImpl;
        newsList = new ArrayList<>();
    }

    @GetMapping("/articles")
    public String showNews(Model model) {
//        get articles from DB
        newsList = newsDaoImpl.findAll();
//        add to list articles from RestApi
        newsList.addAll(newsService.getNews().getArticles());
        model.addAttribute("newsList", newsList);
        return "allArticles";
    }

    @PostMapping("/save-article/{index}")
    public String saveArticle(@PathVariable("index") int index) {
        newsDaoImpl.saveNews(newsList.get(index));
        return "redirect:/articles";
    }

    @PostMapping("/edit-article/{id}")
    public String editNews(Model model, @PathVariable("id") long id) {
        model.addAttribute("article", newsDaoImpl.findArticleById(id));
        return "editArticle";
    }

    @PostMapping("/edit-article-execute")
    public String updateNews(@ModelAttribute Article article) {
        newsDaoImpl.updateArticle(article);
        return "redirect:/articles";
    }

    @PostMapping("/delete-article/{id}")
    public String deleteNews(@PathVariable("id") long id) {
        newsDaoImpl.deleteArticle(id);
        return "redirect:/articles";
    }

}



