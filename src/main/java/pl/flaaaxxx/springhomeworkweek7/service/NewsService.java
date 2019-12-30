package pl.flaaaxxx.springhomeworkweek7.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.flaaaxxx.springhomeworkweek7.dao.NewsDaoImpl;
import pl.flaaaxxx.springhomeworkweek7.model.News;

@Service
public class NewsService {

    private News news;
    private NewsDaoImpl newsDaoImpl;

    public NewsService(NewsDaoImpl newsDaoImpl) {
        this.newsDaoImpl = newsDaoImpl;
    }

    public News getNews() {
        RestTemplate restTemplate = new RestTemplate();
        news = restTemplate.getForObject("https://newsapi.org/v2/everything?domains=wsj.com,nytimes.com&apiKey=f8b6fd7c6ab5402a9a71d2656bb23eab", News.class);
        return news;
    }
}
