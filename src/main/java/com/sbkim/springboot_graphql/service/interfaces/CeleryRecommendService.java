package com.sbkim.springboot_graphql.service.interfaces;

import com.sbkim.springboot_graphql.domain.Content;
import java.util.List;

public interface CeleryRecommendService {

    /**
     * 사용자에게 추천 Content 를 가져오는 Celery Node 를 호출한다.
     *
     * @param userId
     */
    List<Content> getRecommendedContent(String userId);

}
