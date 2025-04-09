package jdbc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @auther yangxiaolong
 * @create 2025/4/9
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource.hikari")
public class MyHikariConfig extends HikariConfig {

    @Bean
    public HikariDataSource dataSource(MyHikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

}
