package com.sbkim.springboot_graphql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.sbkim.springboot_graphql.domain.Comment;
import com.sbkim.springboot_graphql.domain.Content;
import com.sbkim.springboot_graphql.repository.CommentRepository;
import com.sbkim.springboot_graphql.repository.ContentRepository;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// TC 파일
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphQLTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

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

    @DisplayName("GraphQL 의 응답이 오는지 확인한다.")
    @Test
    void queryTEST() throws IOException {

        // when
        GraphQLResponse response = graphQLTestTemplate.postForResource(
            "queryTest_getContentList.graphql");

        // then
        System.out.println(response.readTree().toString());
        assertTrue(response.isOk());
    }

    @DisplayName("GraphQL 의 응답 결과가 예상과 맞는지 확인한다. - Content Array")
    @Test
    public void givenContentAndComment_whenRequest_thenReturnData() throws Exception {

        //when
        GraphQLResponse response = graphQLTestTemplate.postForResource(
            "queryTest_getContentList.graphql");

        //then
        assertTrue(response.isOk());
        System.out.println(response.readTree().toString());
        assertEquals("1", response.get("$.data.getContentList[0].contentId"));
        assertEquals(contentTitle1, response.get("$.data.getContentList[0].contentTitle"));
        assertEquals(contentBody1, response.get("$.data.getContentList[0].contentBody"));
        assertEquals("1", response.get("$.data.getContentList[0].commentList[0].commentId"));
        assertEquals(commentBody,
            response.get("$.data.getContentList[0].commentList[0].commentBody"));
    }

    @DisplayName("GraphQL 의 응답 결과가 예상과 맞는지 확인한다. - Content")
    @Test
    public void givenContentAndComment_whenRequestContent_thenReturnContent() throws Exception {

        //when
        GraphQLResponse response = graphQLTestTemplate.postForResource(
            "queryTest_getContent.graphql");

        //then
        System.out.println(response.readTree().toString());
        assertTrue(response.isOk());
        assertEquals("2", response.get("$.data.getContent.contentId"));
    }
}
