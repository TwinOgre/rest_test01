package com.restTest.proj2.domain.article.controller;

import com.restTest.proj2.domain.article.entity.Article;
import com.restTest.proj2.domain.article.service.ArticleService;
import com.restTest.proj2.global.RsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class APIV1ArticleController {
    private final ArticleService articleService;

    @AllArgsConstructor
    @Getter
    private static class ArticlesResponce {
        private final List<Article> articleList;
    }

    @GetMapping("")
    private RsData<ArticlesResponce> getArticles() {
        List<Article> articleList = this.articleService.getArticles();

        return RsData.of(
                "S-1",
                "다건 요청 성공",
                new ArticlesResponce(articleList)
        );
    }

    @AllArgsConstructor
    @Getter
    private static class ArticleResponce {
        private final Article article;
    }

    @GetMapping("/{id}")
    private RsData<ArticleResponce> getArticle(@PathVariable("id") Long id) {
        Optional<Article> optionalArticle = this.articleService.getArticle(id);
        if (optionalArticle.isEmpty()) {
            return RsData.of(
                    "F-2",
                    "단건 요청 실패: 존재하지 않는 ID",
                    null
            );
        }
        return RsData.of(
                "S-2",
                "단건 요청 성공",
                new ArticleResponce(optionalArticle.get())
        );
    }

    @Data
    private static class CreateArticle {
        @NotBlank
        private String subject;
        @NotBlank
        private String content;
    }

    @AllArgsConstructor
    @Getter
    private static class ArticleCreateResponce {
        private final Article article;
    }

    @PostMapping("")
    private RsData<ArticleCreateResponce> createArticle(@Valid @RequestBody CreateArticle createArticle) {
        Article article = this.articleService.createArticle(createArticle.getSubject(), createArticle.getContent());
        if (article == null) {
            return RsData.of(
                    "F-3",
                    "게시글 등록 실패",
                    null
            );
        }
        return RsData.of(
                "S-3",
                "게시글 등록 성공",
                new ArticleCreateResponce(article)
        );
    }

    @Data
    private static class ModifyArticleBody {
        @NotBlank
        private String subject;
        @NotBlank
        private String content;
    }

    @AllArgsConstructor
    @Getter
    private static class ArticleModifyResponce {
        private final Article article;
    }

    @PatchMapping("/{id}")
    private RsData<ArticleModifyResponce> modifyArticle(@PathVariable("id") Long id, @Valid @RequestBody ModifyArticleBody modifyArticleBody) {
        Optional<Article> optionalArticle = this.articleService.findById(id);
        if (optionalArticle.isEmpty()) {
            return RsData.of(
                    "F-4",
                    "수정 실패: %d번 ID 존재하지 않음.".formatted(id),
                    null
            );
        }
        RsData<Article> rsData = this.articleService.modifyArticle(optionalArticle.get(), modifyArticleBody.getSubject(), modifyArticleBody.getContent());
        return RsData.of(
                rsData.getResultCode(),
                rsData.getMsg(),
                new ArticleModifyResponce(rsData.getData())
        );
    }

    @AllArgsConstructor
    @Getter
    private static class ArticleDeleteResponce {
        private final Article article;
    }

    @DeleteMapping("/{id}")
    private RsData<ArticleDeleteResponce> deleteArticle(@PathVariable("id") Long id) {
        Optional<Article> optionalArticle = this.articleService.findById(id);
        if (optionalArticle.isEmpty()) {
            return RsData.of(
                    "F-5",
                    "삭제 실패: %d번 ID 존재하지 않음.".formatted(id),
                    null
            );
        }
        RsData<Article> rsData = this.articleService.deleteArticle(optionalArticle.get());
        return RsData.of(
                rsData.getResultCode(),
                rsData.getMsg(),
                new ArticleDeleteResponce(rsData.getData())
        );
    }
}
