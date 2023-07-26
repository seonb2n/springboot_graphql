package com.sbkim.springboot_graphql.resolver;

import com.sbkim.springboot_graphql.repository.CommentRepository;
import com.sbkim.springboot_graphql.repository.ContentRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

    private final CommentRepository commentRepository;
    private final ContentRepository contentRepository;

    // todo Content를 생성하는 메소드
    public int writeContent(String title, String body) {
        // 여기에 Content를 생성하고 필요한 작업을 수행하는 로직을 추가하세요.

        // Content를 성공적으로 생성했다면, 생성된 Content의 ID나 다른 식별자를 반환합니다.
        // 이 예시에서는 1을 반환합니다.
        return 1;
    }
}
