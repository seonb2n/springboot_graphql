package com.sbkim.springboot_graphql.repository;

import com.sbkim.springboot_graphql.domain.Comment;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByContent_ContentId(Long contentId);

}
