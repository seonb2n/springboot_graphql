package com.sbkim.springboot_graphql.controller;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.sbkim.springboot_graphql.domain.Comment;
import com.sbkim.springboot_graphql.domain.Content;
import com.sbkim.springboot_graphql.repository.CommentRepository;
import com.sbkim.springboot_graphql.repository.ContentRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class GraphQLControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ContentRepository contentRepository;

    String contentTitle1 = "TEST_CONTENT_TITLE";
    String contentBody1 = "TEST_CONTENT_BODY";
    String commentBody = "TEST_COMMNET_BODY";

    String contentTitle2 = "TEST_CONTENT_TITLE2";
    String contentBody2 = "TEST_CONTENT_BODY2";

    @BeforeEach
    void init() {
        Content content = Content.of(contentTitle1, contentBody1);
        content = contentRepository.save(content);
        Comment comment = Comment.of(commentBody, content);
        comment = commentRepository.save(comment);
        content.addComment(comment);

        Content content2 = Content.of(contentTitle2, contentBody2);
        content2 = contentRepository.save(content2);
    }

    @Test
    public void testExecuteGraphQL() throws Exception {
        String query = "{ getContentList { contentId contentTitle contentBody commentList { commentId commentBody } } }";

        webTestClient.
            post()
            .uri("/graphql")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(toJSON(query)), String.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.data.getContentList").isNotEmpty();
    }


    private static String toJSON(String query) {
        try {
            return new JSONObject().put("query", query).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}