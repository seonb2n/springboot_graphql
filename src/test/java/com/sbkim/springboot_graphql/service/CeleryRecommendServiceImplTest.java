package com.sbkim.springboot_graphql.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CeleryRecommendServiceImplTest {

    @Autowired
    private CeleryRecommendServiceImpl celeryRecommendService;

    @Test
    void getRecommendedContent() {
        var celeryResponse = celeryRecommendService.getRecommendedContent("1234");
        assertTrue(celeryResponse.size() > 1);
    }
}