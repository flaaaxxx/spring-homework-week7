package pl.flaaaxxx.springhomeworkweek7.dao;

import pl.flaaaxxx.springhomeworkweek7.model.Article;
import pl.flaaaxxx.springhomeworkweek7.model.News;

import java.util.List;

public interface NewsDao {
    void saveNews(Article article);
    List<Article> findAll();
    Article findByTitle(String title);
    Article findArticleById(long id);
    void updateArticle(Article article);
    void deleteArticle(long id);
}
