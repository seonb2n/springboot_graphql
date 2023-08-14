package com.sbkim.springboot_graphql.service;

import com.geneea.celery.Celery;
import com.sbkim.springboot_graphql.domain.Content;
import com.sbkim.springboot_graphql.service.interfaces.CeleryRecommendService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CeleryRecommendServiceImpl implements CeleryRecommendService {

    @Value("${rabbitmq.brokerUri}")
    private String brokerUri;

    @Value("${rabbitmq.backendUri}")
    private String backendUri;


    @Override
    public List<Content> getRecommendedContent(String userId) {

        try {
            Celery celeryClient = Celery.builder()
                .brokerUri(brokerUri)
                .backendUri(backendUri)
                .queue("task_get_queue0001")
                .build();
            List<List<String>> celeryResult = (List<List<String>>) celeryClient.submit(
                "task.get.recommend.queue", new Object[]{userId}).get();

            List<Content> contentList = new ArrayList<>();
            for (List<String> data : celeryResult) {
                Content content = Content.of(data.get(0), data.get(1));
                contentList.add(content);
            }

            return contentList;
        } catch (Exception e) {
            log.warn("Failed to get recommended content");
        }

        return List.of();
    }
}
