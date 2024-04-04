package com.restTest.proj2.domain.article.service;

import com.restTest.proj2.domain.article.controller.APIV1ArticleController;
import com.restTest.proj2.domain.article.entity.Article;
import com.restTest.proj2.domain.article.repository.ArticleRepository;
import com.restTest.proj2.global.RsData.RsData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getArticles() {
        return this.articleRepository.findAll();
    }

    @Transactional
    public Article createArticle(String subject, String content){
        Article article = Article.builder()
                .subject(subject)
                .content(content)
                .build();
        this.articleRepository.save(article);

        return article;
    }

    public Optional<Article> getArticle(Long id) {
        return this.articleRepository.findById(id);
    }

    public RsData<Article> modifyArticle(Article article, String subject, String content) {
        Article article1 = article.toBuilder()
                .subject(subject)
                .content(content)
                .build();

        this.articleRepository.save(article1);
        return RsData.of(
                "S-4",
                "수정 성공",
                article1
        );
    }

    public Optional<Article> findById(Long id) {
        return this.articleRepository.findById(id);
    }
}
