package com.sbkim.springboot_graphql.resolver;

import com.sbkim.springboot_graphql.domain.Comment;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CommentResolver implements GraphQLResolver<Comment> {

}
