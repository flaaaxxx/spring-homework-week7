package pl.flaaaxxx.springhomeworkweek7.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.flaaaxxx.springhomeworkweek7.DbConfig;
import pl.flaaaxxx.springhomeworkweek7.model.Article;
import pl.flaaaxxx.springhomeworkweek7.model.News;
import pl.flaaaxxx.springhomeworkweek7.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class NewsDaoImpl implements NewsDao {

    private JdbcTemplate jdbcTemplate;

    public NewsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveNews(Article article) {
        String sql = "INSERT INTO news VALUES(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                null,
                article.getTitle(),
                article.getAuthor(),
                article.getContent(),
                article.getUrl(),
                article.getUrlToImage());
    }

    @Override
    public List<Article> findAll() {
        List<Article> newsList = new ArrayList<>();
        String sql = "SELECT * FROM news";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach(element -> newsList.add(new Article(
                Long.parseLong(String.valueOf(element.get("id"))),
                String.valueOf(element.get("author")),
                String.valueOf(element.get("title")),
                String.valueOf(element.get("url")),
                String.valueOf(element.get("urlToImage")),
                String.valueOf(element.get("content"))))
        );
        return newsList;
    }

    @Override
    public Article findByTitle(String title) {
        String sql = "SELECT * FROM news WHERE title = ?";
        return jdbcTemplate.queryForObject(sql, (resultSet, i) ->
                        new Article(
                                resultSet.getLong("id"),
                                resultSet.getString("author"),
                                resultSet.getString("title"),
                                resultSet.getString("url"),
                                resultSet.getString("urlToImage"),
                                resultSet.getString("content"))
                , title);
    }

    @Override
    public void updateArticle(Article article) {
        String sql = "UPDATE news SET title=?, author=?, content=?, url=?, urlToImage=? WHERE id=?";
        jdbcTemplate.update(sql,
                article.getTitle(),
                article.getAuthor(),
                article.getContent(),
                article.getUrl(),
                article.getUrlToImage(),
                article.getId());
    }

    @Override
    public Article findArticleById(long id) {
        String sql = "SELECT * FROM news WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (resultSet, i) ->
                        new Article(
                                resultSet.getLong("id"),
                                resultSet.getString("author"),
                                resultSet.getString("title"),
                                resultSet.getString("url"),
                                resultSet.getString("urlToImage"),
                                resultSet.getString("content"))
                , id);
    }

    @Override
    public void deleteArticle(long id) {
        String sql = "DELETE FROM news WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
