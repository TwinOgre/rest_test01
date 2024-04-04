package com.restTest.proj2.global.initData;

import com.restTest.proj2.domain.article.service.ArticleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev", "test"})
public class NotProd {
    @Bean
    CommandLineRunner initData(ArticleService articleService) {
        return args -> {
            articleService.createArticle("titleNo.1", "contentNo.1");
            articleService.createArticle("titleNo.2", "contentNo.2");
            articleService.createArticle("titleNo.3", "contentNo.3");
            articleService.createArticle("titleNo.4", "contentNo.4");
            articleService.createArticle("titleNo.5", "contentNo.5");
        };
    }
}
