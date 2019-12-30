package pl.flaaaxxx.springhomeworkweek7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    private DataSource dataSource;

    @Autowired
    public DbConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void init(){
//        String sql;
//        sql = "DROP TABLE IF EXISTS news";
//        getJdbcTemplate().update(sql);
//        sql = "CREATE TABLE news(id int AUTO_INCREMENT, title varchar(255), author varchar(255), content varchar(2000), url varchar(355), urlToImage varchar(355), PRIMARY KEY (id))";
//        getJdbcTemplate().update(sql);
//        sql = "DROP TABLE IF EXISTS vehicles";
//        getJdbcTemplate().update(sql);
//        sql = "CREATE TABLE vehicles(id int AUTO_INCREMENT, mark varchar(255), model varchar(255), color varchar(255), productionDate TIMESTAMP, PRIMARY KEY (id))";
//        getJdbcTemplate().update(sql);
//    }
}