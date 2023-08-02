package com.sbkim.springboot_graphql.controller;

import com.sbkim.springboot_graphql.domain.Content;
import com.sbkim.springboot_graphql.repository.ContentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GraphQLController {

    private final ContentRepository contentRepository;

    @QueryMapping
    public List<Content> getContentList() {
        return contentRepository.findAll();
    }

    @MutationMapping
    public Content writeContent(@Argument String title, @Argument String body) {
        Content content = Content.of(title, body);
        contentRepository.save(content);
        return content;
    }
}
