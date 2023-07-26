package com.sbkim.springboot_graphql.resolver;

import com.sbkim.springboot_graphql.domain.Comment;
import com.sbkim.springboot_graphql.domain.Content;
import com.sbkim.springboot_graphql.repository.CommentRepository;
import com.sbkim.springboot_graphql.repository.ContentRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RootQueryResolver implements GraphQLQueryResolver {

    private final ContentRepository contentRepository;
    private final CommentRepository commentRepository;

    public List<Content> getContentList() {
        return contentRepository.findAll();
    }

    public List<Comment> getCommentList() {
        return commentRepository.findAll();
    }
}
