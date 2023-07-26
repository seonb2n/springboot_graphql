package com.sbkim.springboot_graphql.resolver;

import com.sbkim.springboot_graphql.domain.Comment;
import com.sbkim.springboot_graphql.domain.Content;
import com.sbkim.springboot_graphql.repository.CommentRepository;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ContentResolver implements GraphQLResolver<Content> {

    private final CommentRepository commentRepository;

    public List<Comment> commentList(Content content) {
        return commentRepository.findAllByContent_ContentId(content.getContentId());
    }
}
